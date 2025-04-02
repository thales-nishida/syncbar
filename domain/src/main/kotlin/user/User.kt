package user

import AggregateRoot
import validation.ValidationHandler
import java.time.Instant

open class User private constructor(
    val anId: UserID,
    var aName: String?,
    var aEmail: String?,
    var aTypeUser: String?,
    var active: Boolean?,
    val createdAt: Instant = Instant.now(),
    var updatedAt: Instant = Instant.now(),
    var deletedAt: Instant?,
) : AggregateRoot<UserID>(anId) {
    companion object {
        fun newUser(
            name: String?,
            email: String?,
            typeUser: String?,
            isActivate: Boolean?,
            createdAt: Instant = Instant.now(),
            updatedAt: Instant = Instant.now(),
            deletedAt: Instant? = null,
        ): User {
            val id = UserID.unique()
            return User(id, name, email, typeUser, isActivate, createdAt, updatedAt, deletedAt)
        }
    }

    override fun validate(handler: ValidationHandler) {
        UserValidator(this, handler).validate()
    }

    fun update(name: String?, email: String?, typeUser: String?, isActivate: Boolean?): User {
        this.aName = name
        this.aEmail = email
        this.aTypeUser = typeUser
        this.active = isActivate
        return this
    }

    fun deactivate(): User {
        if (this.deletedAt == null) {
            this.deletedAt = Instant.now()
        }
        this.active = false
        this.updatedAt = Instant.now()
        return this
    }

    fun activate(): User {
        this.deletedAt = null
        this.active = true
        this.updatedAt = Instant.now()
        return this
    }
}