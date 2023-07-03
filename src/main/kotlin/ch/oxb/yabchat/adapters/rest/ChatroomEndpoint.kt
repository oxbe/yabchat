package ch.oxb.yabchat.adapters.rest

import ch.oxb.yabchat.adapters.rest.dtos.CreateChatroomDTO
import ch.oxb.yabchat.adapters.rest.dtos.JoinChatroomDTO
import ch.oxb.yabchat.adapters.rest.dtos.LeaveChatroomDTO
import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.chatroom.ChatroomService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Path("/chatroom")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class ChatroomEndpoint(var chatroomService: ChatroomService) {

    @GET
    @Path("/{id}")
    fun getChatroom(id: String): Chatroom? {
        return chatroomService.findChatroomById(id)
    }

    @GET
    fun getChatrooms(): List<Chatroom> {
        return chatroomService.getChatrooms()
    }

    @POST
    fun createChatroom(chatroom: CreateChatroomDTO): Chatroom {
        return chatroomService.createChatroom(chatroom)
    }

    @PUT
    @Path("/{id}/join")
    fun joinRoom(id: String, action: JoinChatroomDTO): Chatroom? {
        return chatroomService.joinChatroom(id, action)
    }

    @PUT
    @Path("/{id}/leave")
    fun leaveRoom(id: String, action: LeaveChatroomDTO): Chatroom? {
        return chatroomService.leaveChatroom(id, action)
    }
}