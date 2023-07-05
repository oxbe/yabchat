package ch.oxb.yabchat.adapters.mongodb.user

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.types.ObjectId

@MongoEntity
class UserProfileEntity {
    var id: ObjectId? = null
    lateinit var userId: String
    lateinit var firstName: String
    lateinit var lastName: String
    lateinit var bio: String
}