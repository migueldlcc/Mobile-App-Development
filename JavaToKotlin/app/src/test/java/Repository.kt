import User.lastName
import User.firstName
import java.util.ArrayList

val User.formattedName: String
    get() {
        return if (lastName != null) {
            if (firstName != null) {
                "$firstName $lastName"
            } else {
                lastName ?: "Unknown"
            }
        } else {
            firstName ?: "Unknown"
        }
    }

object Repository {
    private val _users = mutableListOf(User("Jane", ""), User("John", null), User("Anne", "Doe"))
    val users: List<User>
        get() = _users

    val formattedUserNames: List<String>
        get() = _users.map { user -> user.formattedName }

    init {
        val user1 = User("Jane", "")
        val user2 = User("John", null)
        val user3 = User("Anne", "Doe")

        _users.apply {
            //this == _users
            add(user1)
            add(user2)
            add(user3)
        }
    }
}

