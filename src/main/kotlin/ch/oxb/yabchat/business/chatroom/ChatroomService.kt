package ch.oxb.yabchat.business.chatroom

import ch.oxb.yabchat.adapters.rest.dtos.CreateChatroomDTO
import ch.oxb.yabchat.business.user.UserService
import jakarta.enterprise.context.ApplicationScoped
import java.util.*


@ApplicationScoped
class ChatroomService(val userService: UserService) {

    private var rooms = mutableListOf(
        Chatroom(
            "bdf79854-cf41-4615-80b2-6a69b479874f",
            "Zühlke Chat",
            "Alice 42 Zühlke Chat",
            listOf()
        ),
        Chatroom(
            "d9667cec-1b61-4b59-8970-a8c3d3704e95",
            "SBB Chat",
            "Automaten Team Chat",
            listOf()
        ),
    )

    fun createChatroom(createChatroom: CreateChatroomDTO): Chatroom {
        val chatroom = Chatroom(
            UUID.randomUUID().toString(),
            createChatroom.name,
            createChatroom.description,
            listOf()
        )
        rooms.add(chatroom);
        return chatroom
    }

    fun getChatrooms(): List<Chatroom> {
        return rooms
    }

    fun findChatroomById(id: String): Chatroom? {
        return rooms.find { room: Chatroom -> room.id == id }
    }

    fun joinChatroom(chatroomId: String, userId: String): Chatroom? {
        val user = userService.findUserById(userId)
        val chatroom = rooms.find { chatroom ->  chatroom.id == chatroomId }

        if (user != null && chatroom != null) {
            chatroom.users = chatroom.users.plus(user)
        }

        return chatroom
    }

    fun leaveChatroom(chatroomId: String, userId: String): Chatroom? {
        val user = userService.findUserById(userId)
        val chatroom = rooms.find { chatroom ->  chatroom.id == chatroomId }

        if (user != null && chatroom != null) {
            chatroom.users = chatroom.users.minus(user)
        }

        return chatroom
    }
}