package ch.oxb.yabchat.adapters.rest

import ch.oxb.yabchat.adapters.rest.dtos.CreateUserDTO
import ch.oxb.yabchat.business.User
import ch.oxb.yabchat.business.UserProfile
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/user")
class UserEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    fun getUser(id: String): User {
        return User(id, "abc", "abc@gmail.com", UserProfile("Alex", "Rüedlinger", "I like velos"))
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getUsers(): List<User> {
        return listOf(User("-1", "abc", "abc@gmail.com", UserProfile("Alex", "Rüedlinger", "I like velos")))
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    fun createUser(user: CreateUserDTO): User {
        return User("1", user.username, user.email, UserProfile("", "", ""))
    }
}