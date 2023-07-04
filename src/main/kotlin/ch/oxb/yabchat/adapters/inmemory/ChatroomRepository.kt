package ch.oxb.yabchat.adapters.inmemory

import ch.oxb.yabchat.adapters.rest.dtos.CreateChatroomDTO
import ch.oxb.yabchat.business.chatroom.Chatroom
import jakarta.enterprise.context.ApplicationScoped
import java.util.*

@ApplicationScoped
class ChatroomRepository {

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

    fun findChatroomById(chatroomId: String): Chatroom? {
        return rooms.find { room: Chatroom -> room.id == chatroomId }
    }
}