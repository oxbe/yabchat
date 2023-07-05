package ch.oxb.yabchat.adapters.rest

import ch.oxb.yabchat.business.message.Message
import ch.oxb.yabchat.business.message.MessageService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/message")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class MessageEndpoint(var messageService: MessageService) {

    @GET
    fun getAllMessages(): List<Message> {
        return messageService.getAllMessages()
    }
}