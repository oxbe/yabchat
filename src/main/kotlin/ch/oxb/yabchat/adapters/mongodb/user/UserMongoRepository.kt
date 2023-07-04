package ch.oxb.yabchat.adapters.mongodb.user

import io.quarkus.mongodb.panache.kotlin.PanacheMongoRepository
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId

@ApplicationScoped
class UserMongoRepository: PanacheMongoRepository<UserEntity> {
    fun findUserById(id: String) = findById(ObjectId(id))
    fun findUserByEmail(email: String) = find("email", email).firstResult()
    fun findUserByUsername(email: String) = find("username", email).firstResult()
    fun deleteUserById(id: String) = deleteById(ObjectId(id))
    fun getUsers() = listAll()
}