package ch.oxb.yabchat.business.user

import ch.oxb.yabchat.adapters.rest.dtos.CreateUserDTO
import jakarta.enterprise.context.ApplicationScoped
import java.util.UUID

@ApplicationScoped
class UserService {

    private var users = mutableListOf(
        User("433346b2-c3c3-429b-87ea-8728765ae546", "lexruee", "alex@example.com", UserProfile("Alex", "Rüedlinger", "I like velos")),
        User("6ae58e08-d05c-4dd6-b2cc-8601eb259f5f", "no1cheats", "jan@eample.com", UserProfile("Jan", "Imhof", "I like planes")),
    )

    fun createUser(createUser: CreateUserDTO): User {
        val user = User(
            UUID.randomUUID().toString(),
            createUser.username,
            createUser.email,
            null
        )
        users.add(user)
        return user
    }

    fun getUsers(): List<User> {
        return users
    }

    fun findUserById(id: String): User? {
        return users.find { user: User -> user.id == id }
    }
}