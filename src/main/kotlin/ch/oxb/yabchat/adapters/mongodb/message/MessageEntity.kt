package ch.oxb.yabchat.adapters.mongodb.message

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.types.ObjectId
import java.time.Instant

@MongoEntity
class MessageEntity {
    var id: ObjectId? = null
    lateinit var senderUserId: String
    lateinit var senderName: String
    lateinit var content: String
    lateinit var timestamp: Instant
}