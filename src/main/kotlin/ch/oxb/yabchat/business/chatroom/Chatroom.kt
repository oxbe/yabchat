package ch.oxb.yabchat.business.chatroom

import ch.oxb.yabchat.business.user.User

class Chatroom(val id: String,
               val name: String,
               val description: String,
               var userIds: List<String>)