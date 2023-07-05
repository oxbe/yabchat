package ch.oxb.yabchat.adapters.graphql.message

import ch.oxb.yabchat.adapters.mongodb.message.MessageEntity
import ch.oxb.yabchat.business.message.Message
import ch.oxb.yabchat.business.message.MessageService
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Mutation
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class MessageResource {

    @Inject
    lateinit var messageService: MessageService

    @Query("allMessages")
    @Description("Get all messages")
    fun getAllUsers(): List<Message> {
        return messageService.getAllMessages()
    }

    @Mutation
    @Description("Create a message")
    fun createMessage(messageEntity: MessageEntity): Message {
        return messageService.saveMessage(messageEntity)
    }

    @Mutation
    @Description("Delete a message")
    fun deleteMessage(messageEntity: MessageEntity){
        messageService.deleteMessage(messageEntity)
    }
}