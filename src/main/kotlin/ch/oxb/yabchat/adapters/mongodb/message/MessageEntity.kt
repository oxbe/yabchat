package ch.oxb.yabchat.adapters.mongodb.message

import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.user.User
import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.types.ObjectId
import java.time.OffsetDateTime

@MongoEntity
class MessageEntity {
    var id: ObjectId? = null
    lateinit var sender: User
    lateinit var recipient: Chatroom
    lateinit var content: String
    lateinit var timestamp: OffsetDateTime
}