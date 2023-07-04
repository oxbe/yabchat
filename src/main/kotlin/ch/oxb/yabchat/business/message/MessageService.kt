package ch.oxb.yabchat.business.message

import ch.oxb.yabchat.adapters.mongodb.message.MessageEntity
import ch.oxb.yabchat.adapters.mongodb.message.MessageMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId

@ApplicationScoped
class MessageService(
    val messageMongoRepository: MessageMongoRepository
) {

    fun saveMessage(message: Message): Message {
        val messageEntity = createMessageEntity(message)
        messageMongoRepository.persist(messageEntity)
        return message
    }

    fun getAllMessages(): List<Message> {
        return messageMongoRepository.getAllMessages().map { messageEntity -> createMessage(messageEntity) }
    }

    private fun createMessage(messageEntity: MessageEntity) = Message(
        messageEntity.sender,
        messageEntity.recipient,
        messageEntity.content,
        messageEntity.timestamp
    )

    private fun createMessageEntity(message: Message): MessageEntity {
        val messageEntity = MessageEntity()
        messageEntity.id = ObjectId()
        messageEntity.content = message.content
        messageEntity.recipient = message.recipient
        messageEntity.sender = message.sender
        messageEntity.timestamp = message.timestamp
        return messageEntity
    }
}