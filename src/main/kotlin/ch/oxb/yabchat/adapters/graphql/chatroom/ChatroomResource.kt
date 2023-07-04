package ch.oxb.yabchat.adapters.graphql.chatroom

import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.chatroom.ChatroomService
import ch.oxb.yabchat.business.user.User
import ch.oxb.yabchat.business.user.UserService
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query
import org.eclipse.microprofile.graphql.Source

@GraphQLApi
class ChatroomResource {

    @Inject
    lateinit var chatroomService: ChatroomService

    @Inject
    lateinit var userService: UserService

    @Query("allChatrooms")
    @Description("Get all chatrooms")
    fun getAllChatrooms(): List<Chatroom> {
        return chatroomService.getChatrooms()
    }

    fun users(@Source chatroom: Chatroom): List<User> {
        return userService.getUsers()
            .filter { user: User ->  chatroom.userIds.contains(user.id) }
    }
}