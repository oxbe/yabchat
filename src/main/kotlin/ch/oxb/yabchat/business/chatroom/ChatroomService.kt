package ch.oxb.yabchat.business.chatroom

import ch.oxb.yabchat.adapters.rest.dtos.CreateChatroomDTO
import ch.oxb.yabchat.adapters.rest.dtos.JoinChatroomDTO
import ch.oxb.yabchat.adapters.rest.dtos.LeaveChatroomDTO
import ch.oxb.yabchat.business.user.UserService
import jakarta.enterprise.context.ApplicationScoped


@ApplicationScoped
class ChatroomService(val userService: UserService, val chatroomRepository: ChatroomRepository) {

    fun createChatroom(createChatroomDTO: CreateChatroomDTO): Chatroom {
        return chatroomRepository.createChatroom(createChatroomDTO)
    }

    fun getChatrooms(): List<Chatroom> {
        return chatroomRepository.getChatrooms()
    }

    fun findChatroomById(chatroomId: String): Chatroom? {
        return chatroomRepository.findChatroomById(chatroomId)
    }

    fun joinChatroom(chatroomId: String, action: JoinChatroomDTO): Chatroom? {
        val user = userService.findUserById(action.userId)
        val chatroom = chatroomRepository.findChatroomById(chatroomId)

        if (user != null && chatroom != null) {
            chatroom.users = chatroom.users.plus(user).distinct()
        }

        return chatroom
    }

    fun leaveChatroom(chatroomId: String, action: LeaveChatroomDTO): Chatroom? {
        val user = userService.findUserById(action.userId)
        val chatroom = chatroomRepository.findChatroomById(chatroomId)

        if (user != null && chatroom != null) {
            chatroom.users = chatroom.users.minus(user)
        }

        return chatroom
    }
}