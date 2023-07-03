package ch.oxb.yabchat.adapters.mongodb.user

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId

@ApplicationScoped
class UserMongoRepository: PanacheMongoRepository<UserEntity> {
    fun findUserById(id: String) = findById(ObjectId(id))
    fun getUsers() = listAll()
}