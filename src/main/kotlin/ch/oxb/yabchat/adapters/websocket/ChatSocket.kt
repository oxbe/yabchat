package ch.oxb.yabchat.adapters.websocket

import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.message.Message
import ch.oxb.yabchat.business.message.MessageService
import ch.oxb.yabchat.business.user.User
import ch.oxb.yabchat.business.user.UserService
import io.quarkus.arc.ComponentsProvider.LOG
import jakarta.enterprise.context.ApplicationScoped
import jakarta.websocket.*
import jakarta.websocket.server.PathParam
import jakarta.websocket.server.ServerEndpoint
import java.time.OffsetDateTime
import java.time.ZonedDateTime
import java.util.concurrent.ConcurrentHashMap
import java.util.function.Consumer


@ServerEndpoint("/chat/{username}")
@ApplicationScoped
class ChatSocket(
    val userService: UserService,
    val messageService: MessageService
) {

    var sessions: MutableMap<User?, Session> = ConcurrentHashMap()

    val chatroom: Chatroom = Chatroom("123", "SBB Automaten", "Automatenchat", mutableListOf())

    @OnOpen
    fun onOpen(session: Session, @PathParam("username") username: String) {
        val user = userService.findUserByUsername(username)
        LOG.info("Open connection for user: ${user?.username}")
        sessions[user] = session
    }

    @OnClose
    fun onClose(session: Session?, @PathParam("username") username: String) {
        val user = userService.findUserByUsername(username)
        LOG.info("Close connection for user: ${user?.username}")
        sessions.remove(user)
        broadcast("User ${user?.username} left")
    }

    @OnError
    fun onError(session: Session?, @PathParam("username") username: String, throwable: Throwable) {
        val user = userService.findUserByUsername(username)
        LOG.info("Close connection on error for user: ${user?.username}")
        sessions.remove(user)
        broadcast("User ${user?.username} left on error: $throwable")
    }

    @OnMessage
    fun onMessage(messageContent: String, @PathParam("username") username: String) {
        val user = userService.findUserByUsername(username)
        val message = Message(user!!.id, user.username, messageContent, ZonedDateTime.now())
        messageService.saveMessage(message)
        if (message.content.equals("_ready_", ignoreCase = true)) {
            broadcast("User ${user.username} joined")
        } else {
            broadcast(">> ${user.username}: ${message.content}")
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