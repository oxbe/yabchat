package ch.oxb.yabchat.adapters.graphql.user

class UpdateUserProfileEntity {
    lateinit var userId: String
    var firstName: String? = null
    var lastName: String? = null
    var bio: String? = null
}
