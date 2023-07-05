package ch.oxb.yabchat.adapters.mongodb.user

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserProfileMongoRepository: PanacheMongoRepository<UserProfileEntity> {
    fun findUserProfileByUserId(userId: String) = find("userId",userId).firstResult()
    fun getUserProfiles() = listAll()
}