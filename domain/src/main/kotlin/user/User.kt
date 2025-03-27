package user

import AggregateRoot
import validation.ValidationHandler

open class User private constructor(
    val anId: UserID,
    var aName: String?,
    var aEmail: String?,
    var aPassword: String?,
    var aTypeUser: String?,
    var isActivate: Boolean?
) : AggregateRoot<UserID>(anId) {
    companion object {
        fun newUser(name: String?, email: String?, password: String?, typeUser: String?, isActivate: Boolean? = true): User {
            val id = UserID.unique()
            return User(id, name, email, password, typeUser, isActivate)
        }
    }

    override fun validate(handler: ValidationHandler) {
        UserValidator(this, handler).validate()
    }

    fun getUpdateName(name: String, email: String?, password: String?, typeUser: String?, isActivate: Boolean?): User {
        this.aName = name
        this.aEmail = email
        this.aPassword = password
        this.aTypeUser = typeUser
        this.isActivate = isActivate
        return this
    }
}