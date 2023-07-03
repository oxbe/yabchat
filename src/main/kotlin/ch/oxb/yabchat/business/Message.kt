package ch.oxb.yabchat.business

import ch.oxb.yabchat.business.user.User
import java.time.ZonedDateTime

class Message(val sender: User, val recipient: Chatroom, val content: String, val timestamp: ZonedDateTime) {
}