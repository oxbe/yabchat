package ch.oxb.yabchat.business.user

import ch.oxb.yabchat.adapters.graphql.user.UpdateUserProfileEntity
import ch.oxb.yabchat.adapters.mongodb.user.UserProfileEntity
import ch.oxb.yabchat.adapters.mongodb.user.UserProfileMongoRepository
import ch.oxb.yabchat.adapters.rest.dtos.CreateUserProfileDTO
import ch.oxb.yabchat.adapters.rest.dtos.UpdateUserProfileDTO
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId

@ApplicationScoped
class UserProfileService(val userProfileMongoRepository: UserProfileMongoRepository) {

    fun saveUserProfile(userId: String, userCreateUserProfileDTO: CreateUserProfileDTO): UserProfile {
        val userProfileEntity = createUserProfileEntity(userId, userCreateUserProfileDTO)
        userProfileMongoRepository.persist(userProfileEntity)
        return createUserProfile(userProfileEntity)
    }

    fun updateUserProfile(id: String, updateUserProfileDTO: UpdateUserProfileDTO): UserProfile? {
        return userProfileMongoRepository.findUserProfileByUserId(id)
            ?.let { entity -> updateUserProfileEntity(entity, updateUserProfileDTO) }
            ?.let { entity -> userProfileMongoRepository.update(entity); entity }
            ?.let { entity ->  createUserProfile(entity) }
    }

    fun updateUserProfile(updateUserProfileEntity: UpdateUserProfileEntity): UserProfile? {
        return userProfileMongoRepository.findUserProfileByUserId(updateUserProfileEntity.userId)
            ?.let { entity -> updateUserProfileEntity(entity, updateUserProfileEntity) }
            ?.let { entity -> userProfileMongoRepository.update(entity); entity }
            ?.let { entity ->  createUserProfile(entity) }
    }

    fun findUserProfileByUserId(userId: String): UserProfile? {
        return userProfileMongoRepository.findUserProfileByUserId(userId)
            ?.let { userProfileEntity -> createUserProfile(userProfileEntity) }
    }

    fun getUserProfiles(): List<UserProfile> {
        return userProfileMongoRepository.getUserProfiles()
            .map { userProfileEntity -> createUserProfile(userProfileEntity) }
    }

    private fun createUserProfile(userProfileEntity: UserProfileEntity) = UserProfile(
        userProfileEntity.userId,
        userProfileEntity.firstName,
        userProfileEntity.lastName,
        userProfileEntity.bio
    )

    private fun createUserProfileEntity(userId: String, createUserProfileDTO: CreateUserProfileDTO): UserProfileEntity {
        return UserProfileEntity().let { userProfileEntity ->
            userProfileEntity.id = ObjectId()
            userProfileEntity.userId = userId
            userProfileEntity.firstName = createUserProfileDTO.firstName
            userProfileEntity.lastName = createUserProfileDTO.lastName
            userProfileEntity.bio = createUserProfileDTO.bio
            userProfileEntity
        }
    }

    private fun updateUserProfileEntity(userProfileEntity: UserProfileEntity,
                                        updateUserProfileDTO: UpdateUserProfileDTO): UserProfileEntity {
        return userProfileEntity.apply {
            this.firstName = updateUserProfileDTO.firstName
            this.lastName = updateUserProfileDTO.lastName
            this.bio = updateUserProfileDTO.bio
        }
    }

    private fun updateUserProfileEntity(userProfileEntity: UserProfileEntity,
                                        updateUserProfileEntity: UpdateUserProfileEntity): UserProfileEntity {
        return userProfileEntity.apply {
            this.firstName = updateUserProfileEntity.firstName.toString()
            this.lastName = updateUserProfileEntity.lastName.toString()
            this.bio = updateUserProfileEntity.bio.toString()
        }
    }
}