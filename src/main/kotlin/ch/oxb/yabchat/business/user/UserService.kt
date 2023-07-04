package ch.oxb.yabchat.business.user

import ch.oxb.yabchat.adapters.mongodb.user.UserEntity
import ch.oxb.yabchat.adapters.mongodb.user.UserMongoRepository
import ch.oxb.yabchat.adapters.rest.dtos.CreateUserDTO
import jakarta.enterprise.context.ApplicationScoped
import org.bson.types.ObjectId

@ApplicationScoped
class UserService(val userMongoRepository: UserMongoRepository) {

    fun saveUser(createUserDTO: CreateUserDTO): User? {
        val userEntity = createUserEntity(createUserDTO)
        userMongoRepository.persist(userEntity)
        return createUser(userEntity)
    }

    fun saveUser(userEntity: UserEntity): User? {
        userMongoRepository.persist(userEntity)
        return createUser(userEntity)
    }

    fun getUsers(): List<User> {
        return userMongoRepository.getUsers().map { u -> createUser(u) }
    }

    fun findUserById(userId: String): User? {
        return userMongoRepository.findUserById(userId)
            ?.let { userEntity -> createUser(userEntity) }
    }

    fun findUserByEmail(email: String): User? {
        return userMongoRepository.findUserByEmail(email)
            ?.let { userEntity -> createUser(userEntity) }
    }

    fun findUserByUsername(username: String): User? {
        return userMongoRepository.findUserByUsername(username)
            ?.let { userEntity -> createUser(userEntity) }
    }

    private fun createUser(userEntity: UserEntity) = User(
        userEntity.id.toString(),
        userEntity.username,
        userEntity.email
    )

    private fun createUserEntity(createUserDTO: CreateUserDTO): UserEntity {
        val u = UserEntity()
        u.id = ObjectId()
        u.email = createUserDTO.email
        u.username = createUserDTO.username
        return u
    }

    fun deleteUser(userId: String) {
        userMongoRepository.deleteUserById(userId)
    }
}