package ch.oxb.yabchat.adapters.graphql.chatroom

import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.chatroom.ChatroomService
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class ChatroomResource {

    @Inject
    lateinit var chatroomService: ChatroomService

    @Query("allChatrooms")
    @Description("Get all chatrooms")
    fun getAllChatrooms(): List<Chatroom> {
        return chatroomService.getChatrooms()
    }
}