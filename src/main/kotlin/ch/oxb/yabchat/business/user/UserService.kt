package ch.oxb.yabchat.business.user

import ch.oxb.yabchat.adapters.inmemory.UserRepository
import ch.oxb.yabchat.adapters.rest.dtos.CreateUserDTO
import jakarta.enterprise.context.ApplicationScoped

@ApplicationScoped
class UserService(val userRepository: UserRepository) {

    fun createUser(createUserDTO: CreateUserDTO): User {
        return userRepository.createUser(createUserDTO)
    }

    fun getUsers(): List<User> {
        return userRepository.getUsers()
    }

    fun findUserById(userId: String): User? {
        return userRepository.findUserById(userId)
    }
}