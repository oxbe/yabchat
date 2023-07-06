package ch.oxb.yabchat.adapters.graphql.user

import ch.oxb.yabchat.business.user.User
import ch.oxb.yabchat.business.user.UserProfile
import ch.oxb.yabchat.business.user.UserProfileService
import ch.oxb.yabchat.business.user.UserService
import jakarta.inject.Inject
import org.eclipse.microprofile.graphql.*

@GraphQLApi
class UserProfileResource {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var userProfileService: UserProfileService

    @Query("allUserProfiles")
    @Description("Get all user profiles")
    fun getAllUserProfiles(): List<UserProfile> {
        return userProfileService.getUserProfiles()
    }

    @Query
    @Description("Get a user profile")
    fun getUserProfile(@Name("userId") userId: String): UserProfile? {
        return userProfileService.findUserProfileByUserId(userId)
    }

    fun user(@Source userProfile: UserProfile): User? {
        return userService.findUserById(userProfile.userId)
    }

    @Mutation
    @Description("Update a user profile")
    fun updateUserProfile(updateUserProfileEntity: UpdateUserProfileEntity): UserProfile? {
        return userProfileService.updateUserProfile(updateUserProfileEntity)
    }

}