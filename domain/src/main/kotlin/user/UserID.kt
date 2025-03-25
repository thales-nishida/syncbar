package user

import Identifier
import java.util.*

class UserID constructor(private val value: String) : Identifier() {

    companion object {
        fun unique(): UserID {
            return UserID(UUID.randomUUID().toString())
        }

        fun from(anId: String): UserID {
            require(anId.isNotEmpty()) { "UserId can't be empty" }
            return UserID(anId)
        }

        fun from(anId: UUID): UserID {
            return UserID(anId.toString().lowercase(Locale.getDefault()))
        }
    }

    fun getValue(): String {
        return value
    }
}
