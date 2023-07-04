package ch.oxb.yabchat.adapters.websocket

import ch.oxb.yabchat.business.user.UserService
import io.quarkus.arc.ComponentsProvider.LOG
import jakarta.enterprise.context.ApplicationScoped
import jakarta.websocket.*
import jakarta.websocket.server.PathParam
import jakarta.websocket.server.ServerEndpoint
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer


@ServerEndpoint("/chat/{username}")
@ApplicationScoped
class ChatSocket(val userService: UserService) {

    var sessions: MutableMap<String, Session> = ConcurrentHashMap()

    @OnOpen
    fun onOpen(session: Session, @PathParam("username") username: String) {
        val user = userService.findUserByUsername(username)
        LOG.info("Open connection for user: $username")
        sessions[username] = session
    }

    @OnClose
    fun onClose(session: Session?, @PathParam("username") username: String) {
        LOG.info("Close connection for user: $username")
        sessions.remove(username)
        broadcast("User $username left")
    }

    @OnError
    fun onError(session: Session?, @PathParam("username") username: String, throwable: Throwable) {
        LOG.info("Close connection on error for user: $username")
        sessions.remove(username)
        broadcast("User $username left on error: $throwable")
    }

    @OnMessage
    fun onMessage(message: String, @PathParam("username") username: String) {
        if (message.equals("_ready_", ignoreCase = true)) {
            LOG.info("User $username joined")
            broadcast("User $username joined")
        } else {
            LOG.info("broadcast message from user: $username")
            broadcast(">> $username: $message")
        }
    }

    private fun broadcast(message: String) {
        sessions.values.forEach(Consumer { s: Session ->
            s.asyncRemote.sendObject(
                message
            ) { result: SendResult ->
                if (result.exception != null) {
                    println("Unable to send message: " + result.exception)
                }
            }
        })
    }
}