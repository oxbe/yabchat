package ch.oxb.yabchat.business.message

import ch.oxb.yabchat.adapters.mongodb.message.MessageEntity
import ch.oxb.yabchat.adapters.mongodb.message.MessageMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId
import java.time.ZoneId

@ApplicationScoped
class MessageService(
    val messageMongoRepository: MessageMongoRepository
) {

    fun saveMessage(message: Message): Message {
        val messageEntity = createMessageEntity(message)
        messageMongoRepository.persist(messageEntity)
        return message
    }

    fun saveMessage(messageEntity: MessageEntity): Message {
        messageMongoRepository.persist(messageEntity)
        return createMessage(messageEntity)
    }

    fun getAllMessages(): List<Message> {
        return messageMongoRepository.getAllMessages().map { messageEntity -> createMessage(messageEntity) }
    }

    fun deleteMessage(messageEntity: MessageEntity) {
        messageMongoRepository.delete(messageEntity)
    }

    private fun createMessage(messageEntity: MessageEntity) = Message(
        messageEntity.senderUserId,
        messageEntity.senderName,
        messageEntity.content,
        messageEntity.timestamp.atZone(ZoneId.of("Europe/Zurich"))
    )

    private fun createMessageEntity(message: Message): MessageEntity {
        val messageEntity = MessageEntity()
        messageEntity.id = ObjectId()
        messageEntity.senderUserId = message.senderUserId
        messageEntity.senderName = message.senderName
        messageEntity.content = message.content
        messageEntity.timestamp = message.timestamp.toInstant()
        return messageEntity
    }
}