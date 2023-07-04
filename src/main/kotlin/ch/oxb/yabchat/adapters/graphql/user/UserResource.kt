package ch.oxb.yabchat.adapters.graphql.user

import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.chatroom.ChatroomService
import ch.oxb.yabchat.business.user.User
import ch.oxb.yabchat.business.user.UserService
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Name
import org.eclipse.microprofile.graphql.Query
import org.eclipse.microprofile.graphql.Source

@GraphQLApi
class UserResource {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var chatroomService: ChatroomService

    @Query("allUsers")
    @Description("Get all users")
    fun getAllUsers(): List<User> {
        return userService.getUsers()
    }

    fun chatrooms(@Source user: User): List<Chatroom> {
        return chatroomService.getChatrooms()
            .filter { chatroom: Chatroom -> chatroom.userIds.contains(user.id) }
    }

    @Query
    @Description("Get a user")
    fun getUser(@Name("userId") userId: String): User? {
        return userService.findUserById(userId)
    }

    @Query
    @Description("Get a user by email")
    fun getUserWithEmail(email: String): User? {
        return userService.findUserByEmail(email)
    }

    @Query
    @Description("Get a user by username")
    fun getUserWithUsername(username: String): User? {
        return userService.findUserByUsername(username)
    }
}