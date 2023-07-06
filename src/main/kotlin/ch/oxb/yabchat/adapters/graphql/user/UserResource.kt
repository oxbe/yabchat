package ch.oxb.yabchat.adapters.graphql.user

import ch.oxb.yabchat.adapters.mongodb.user.UserEntity
import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.chatroom.ChatroomService
import ch.oxb.yabchat.business.user.User
import ch.oxb.yabchat.business.user.UserProfile
import ch.oxb.yabchat.business.user.UserProfileService
import ch.oxb.yabchat.business.user.UserService
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.*

@GraphQLApi
class UserResource {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var userProfileService: UserProfileService

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

    fun userProfile(@Source user: User): UserProfile? {
        return userProfileService.findUserProfileByUserId(user.id)
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

    @Mutation
    @Description("Create a user")
    fun createUser(userEntity: UserEntity): User? {
        return userService.saveUser(userEntity)
    }

    @Mutation
    @Description("Update a user")
    fun updateUser(userEntity: UserEntity): User? {
        return userService.updateUser(userEntity)
    }

    @Mutation
    @Description("Delete a user")
    fun deleteUser(userId: String) {
        return userService.deleteUser(userId)
    }
}