package ch.oxb.yabchat.adapters.mongodb.chatroom

import io.quarkus.mongodb.panache.common.MongoEntity
import org.bson.types.ObjectId

@MongoEntity
class ChatroomEntity {
    var id: ObjectId? = null
    lateinit var name: String
    lateinit var description: String
    lateinit var userIds: List<String>
}