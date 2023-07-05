package ch.oxb.yabchat.adapters.mongodb.message

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class MessageMongoRepository: PanacheMongoRepository<MessageEntity> {
    fun getAllMessages() = listAll()
}