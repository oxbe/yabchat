package ch.oxb.yabchat.adapters.rest.dtos

import ch.oxb.yabchat.business.User

class CreateChatroomDTO(val name: String, val description: String, val users: List<User>) {
}