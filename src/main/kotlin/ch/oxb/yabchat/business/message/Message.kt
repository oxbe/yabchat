package ch.oxb.yabchat.business.message

import ch.oxb.yabchat.business.chatroom.Chatroom
import ch.oxb.yabchat.business.user.User
import java.time.OffsetDateTime

class Message(val sender: User, val recipient: Chatroom, val content: String, val timestamp: OffsetDateTime)