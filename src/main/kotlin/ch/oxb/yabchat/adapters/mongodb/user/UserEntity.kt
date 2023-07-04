package ch.oxb.yabchat.adapters.mongodb.user

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.types.ObjectId

@MongoEntity
class UserEntity {
    var id: ObjectId? = null
    lateinit var username: String
    lateinit var email: String
}