package ch.oxb.yabchat.adapters.mongodb.chatroom

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId

@ApplicationScoped
class ChatroomMongoRepository: PanacheMongoRepository<ChatroomEntity> {
    fun findChatroomById(id: String) = findById(ObjectId(id))
    fun getChatrooms() = listAll()
}