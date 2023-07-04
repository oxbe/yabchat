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
        return chatroomService.saveChatroom(chatroom)
    }

    @PUT
    @Path("/join")
    fun joinRoom(action: JoinChatroomDTO): Chatroom? {
        return chatroomService.joinChatroom(action)
    }

    @PUT
    @Path("/leave")
    fun leaveRoom(action: LeaveChatroomDTO): Chatroom? {
        return chatroomService.leaveChatroom(action)
    }
}