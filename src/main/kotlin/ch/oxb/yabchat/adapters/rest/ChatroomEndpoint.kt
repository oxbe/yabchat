package ch.oxb.yabchat.adapters.rest

import ch.oxb.yabchat.adapters.rest.dtos.CreateChatroomDTO
import ch.oxb.yabchat.business.Chatroom
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/chatroom")
class ChatroomEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    fun getChatroom(id: String): Chatroom {
        return Chatroom(id, "cool room", "cool description", listOf())
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getChatrooms(): List<Chatroom> {
        return listOf(Chatroom("1", "cool room", "cool description", listOf()))
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun createChatroom(chatroom: CreateChatroomDTO): Chatroom {
        return Chatroom("1", chatroom.name, chatroom.description, chatroom.users)
    }
}