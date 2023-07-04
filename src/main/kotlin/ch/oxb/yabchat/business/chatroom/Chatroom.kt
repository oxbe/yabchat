package ch.oxb.yabchat.business.chatroom

class Chatroom(val id: String,
               val name: String,
               val description: String,
               var userIds: List<String>)