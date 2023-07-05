package ch.oxb.yabchat.business.message

import java.time.ZonedDateTime

class Message(val senderUserId: String, val senderName: String, val content: String, val timestamp: ZonedDateTime)