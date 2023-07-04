package ch.oxb.yabchat.adapters.mongodb.message

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId

@ApplicationScoped
class MessageMongoRepository: PanacheMongoRepository<MessageEntity> {
    fun findUserById(id: String) = findById(ObjectId(id))
    fun getAllMessages() = listAll()
}