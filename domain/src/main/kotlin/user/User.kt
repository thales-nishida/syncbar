package user

import AggregateRoot
import validation.ValidationHandler

open class User private constructor(
    val anId: UserID,
    val aName: String?,
    val aEmail: String?,
    val aPassword: String?,
    val aTypeUser: String?,
) : AggregateRoot<UserID>(anId) {
    companion object {
        fun newUser(name: String?, email: String?, password: String?, typeUser: String?): User {
            val id = UserID.unique()
            return User(id, name, email, password, typeUser)
        }
    }

    override fun validate(handler: ValidationHandler) {
        UserValidator(this, handler).validate()
    }

}