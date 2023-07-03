package ch.oxb.yabchat.adapters.websocket

import jakarta.enterprise.context.ApplicationScoped
import jakarta.websocket.*
import jakarta.websocket.server.PathParam
import jakarta.websocket.server.ServerEndpoint
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer


@ServerEndpoint("/chat/{username}")
@ApplicationScoped
class ChatSocket {

    var sessions: MutableMap<String, Session> = ConcurrentHashMap()


    @OnOpen
    fun onOpen(session: Session, @PathParam("username") username: String) {
        sessions[username] = session
    }

    @OnClose
    fun onClose(session: Session?, @PathParam("username") username: String) {
        sessions.remove(username)
        broadcast("User $username left")
    }

    @OnError
    fun onError(session: Session?, @PathParam("username") username: String, throwable: Throwable) {
        sessions.remove(username)
        broadcast("User $username left on error: $throwable")
    }

    @OnMessage
    fun onMessage(message: String, @PathParam("username") username: String) {
        if (message.equals("_ready_", ignoreCase = true)) {
            broadcast("User $username joined")
        } else {
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