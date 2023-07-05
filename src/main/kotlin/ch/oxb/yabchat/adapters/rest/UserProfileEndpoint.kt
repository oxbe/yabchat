package ch.oxb.yabchat.adapters.rest

import ch.oxb.yabchat.adapters.rest.dtos.CreateUserProfileDTO
import ch.oxb.yabchat.adapters.rest.dtos.UpdateUserProfileDTO
import ch.oxb.yabchat.business.user.UserProfile
import ch.oxb.yabchat.business.user.UserProfileService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/userprofile")
class UserProfileEndpoint(val userProfileService: UserProfileService) {

    @GET
    @Path("/{userId}")
    fun getUserProfile(userId: String): UserProfile? {
        return userProfileService.findUserProfileByUserId(userId)
    }

    @PUT
    @Path("/{userId}")
    fun updateUserProfile(userId: String, updateUserProfileDTO: UpdateUserProfileDTO): UserProfile? {
        return userProfileService.updateUserProfile(userId, updateUserProfileDTO)
    }

    @POST
    @Path("/{userId}")
    fun createUserProfile(userId: String, createUserProfileDTO: CreateUserProfileDTO): UserProfile {
        return userProfileService.saveUserProfile(userId, createUserProfileDTO)
    }

    @GET
    fun getUserProfiles(): List<UserProfile> {
        return userProfileService.getUserProfiles()
    }
}