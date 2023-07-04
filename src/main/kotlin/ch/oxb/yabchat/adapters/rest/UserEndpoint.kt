package ch.oxb.yabchat.adapters.rest

import ch.oxb.yabchat.adapters.rest.dtos.CreateUserDTO
import ch.oxb.yabchat.business.user.User
import ch.oxb.yabchat.business.user.UserService
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.POST
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserEndpoint(var userService: UserService) {

    @GET
    @Path("/{id}")
    fun getUser(id: String): User? {
        return userService.findUserById(id)
    }

    @GET
    fun getUsers(): List<User> {
        return userService.getUsers()
    }

    @POST
    fun createUser(user: CreateUserDTO): User? {
        return userService.saveUser(user)
    }
}