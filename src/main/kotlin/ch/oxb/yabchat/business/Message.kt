package ch.oxb.yabchat.business

import java.time.ZonedDateTime

class Message(val sender: User, val recipient: ChatRoom, val content: String, val timestamp: ZonedDateTime) {
}