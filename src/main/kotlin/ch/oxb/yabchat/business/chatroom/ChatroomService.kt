package ch.oxb.yabchat.business.chatroom

import ch.oxb.yabchat.adapters.mongodb.chatroom.ChatroomEntity
import ch.oxb.yabchat.adapters.mongodb.chatroom.ChatroomMongoRepository
import ch.oxb.yabchat.adapters.rest.dtos.CreateChatroomDTO
import ch.oxb.yabchat.adapters.rest.dtos.JoinChatroomDTO
import ch.oxb.yabchat.adapters.rest.dtos.LeaveChatroomDTO
import ch.oxb.yabchat.business.user.UserService
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId


@ApplicationScoped
class ChatroomService(val userService: UserService,
                      val chatroomMongoRepository: ChatroomMongoRepository) {

    fun saveChatroom(createChatroomDTO: CreateChatroomDTO): Chatroom {
        val chatroomEntity = createChatroomEntity(createChatroomDTO)
        chatroomMongoRepository.persist(chatroomEntity)
        return createChatroom(chatroomEntity)
    }

    fun getChatrooms(): List<Chatroom> {
        return chatroomMongoRepository.getChatrooms()
            .map { chatroomEntity -> createChatroom(chatroomEntity) }
    }

    fun findChatroomById(chatroomId: String): Chatroom? {
        return chatroomMongoRepository.findChatroomById(chatroomId)
            ?.let { chatroomEntity -> createChatroom(chatroomEntity) }
    }

    fun joinChatroom(chatroomId: String, joinChatroomDTO: JoinChatroomDTO): Chatroom? {
        val user = userService.findUserById(joinChatroomDTO.userId)
        val chatroomEntity = chatroomMongoRepository.findChatroomById(chatroomId)

        if (user != null && chatroomEntity != null) {
            chatroomEntity.userIds = chatroomEntity.userIds.plus(joinChatroomDTO.userId).distinct()
            chatroomMongoRepository.update(chatroomEntity)
        }

        return chatroomEntity?.let { createChatroom(chatroomEntity) }
    }

    fun leaveChatroom(chatroomId: String, leaveChatroomDTO: LeaveChatroomDTO): Chatroom? {
        val user = userService.findUserById(leaveChatroomDTO.userId)
        val chatroomEntity = chatroomMongoRepository.findChatroomById(chatroomId)

        if (user != null && chatroomEntity != null) {
            chatroomEntity.userIds = chatroomEntity.userIds.minus(leaveChatroomDTO.userId)
            chatroomMongoRepository.update(chatroomEntity)
        }

        return chatroomEntity?.let { createChatroom(chatroomEntity) }
    }

    private fun createChatroom(chatroomEntity: ChatroomEntity) = Chatroom(
        chatroomEntity.id.toString(),
        chatroomEntity.name,
        chatroomEntity.description,
        chatroomEntity.userIds
    )

    private fun createChatroomEntity(createChatroomDTO: CreateChatroomDTO): ChatroomEntity {
        val chatroomEntity = ChatroomEntity()
        chatroomEntity.id = ObjectId()
        chatroomEntity.name = createChatroomDTO.name
        chatroomEntity.description = createChatroomDTO.description
        chatroomEntity.userIds = listOf()
        return chatroomEntity
    }
}