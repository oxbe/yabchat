package ch.oxb.yabchat.adapters.graphql.user

import ch.oxb.yabchat.business.user.User
import ch.oxb.yabchat.business.user.UserService
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.Description
import org.eclipse.microprofile.graphql.GraphQLApi
import org.eclipse.microprofile.graphql.Query

@GraphQLApi
class UserResource {

    @Inject
    lateinit var userService: UserService

    @Query("allUsers")
    @Description("Get all users")
    fun getAllUsers(): List<User> {
        return userService.getUsers()
    }
}