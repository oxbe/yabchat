package ch.oxb.yabchat.business

import ch.oxb.yabchat.business.user.User

class Chatroom(val id: String, val name: String, val description: String, val users: List<User>) {
}