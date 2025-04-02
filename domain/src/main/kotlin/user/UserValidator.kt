package user

import validation.Error
import validation.ValidationHandler
import validation.Validator

private const val NAME_MIN_LENGTH = 3
private const val MIN_LENGTH_PASSWORD = 8
private const val NAME_MAX_LENGTH = 255
private const val ADMIN = "ADMIN"
private const val EMPLOYEE = "EMPLOYEE"
private const val CLIENT = "CLIENT"


class UserValidator(private val user: User, aHandler: ValidationHandler) : Validator(aHandler) {

    override fun validate() {
        checkConstrainsName()
        checkConstrainsEmail()
        checkConstrainsTypeUser()
    }

    private fun checkConstrainsName() {
        val name = this.user.aName
        if (name == null) {
            this.validationHandler().append(Error("'name' should not be null"))
        }

        name?.let { n ->
            if ((n.isBlank() || n.isEmpty())) {
                this.validationHandler().append(Error("'name' should not be empty"))
            }
            if (n.trim().length <= NAME_MIN_LENGTH || n.trim().length > NAME_MAX_LENGTH) {
                this.validationHandler().append(Error("'name' must between 3 characters and 255 characters"))
            }
        }
    }

    private fun checkConstrainsEmail() {
        val email = this.user.aEmail
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,3}(\\.br)?$".toRegex()
        if (email == null) {
            this.validationHandler().append(Error("'email' should not be null"))
        }
        email?.let {
            if (it.isBlank() || it.isEmpty()) {
                this.validationHandler().append(Error("'email' should not be empty"))
            }
            if (!it.matches(emailRegex)) {
                this.validationHandler().append(Error("'email' format is not valid"))
            }
        }
    }

    private fun checkConstrainsTypeUser() {
        val typeUser = this.user.aTypeUser
        if (typeUser == null) {
            this.validationHandler().append(Error("'typeUser' should not be null"))
        }
        typeUser?.let {
            if (it.isEmpty() || it.isBlank()) {
                this.validationHandler().append(Error("'typeUser' should not be empty"))
            }
            if (!(it.contentEquals(ADMIN) || it.contentEquals(EMPLOYEE) || it.contentEquals(CLIENT))) {
                this.validationHandler().append(Error("'typeUser' format is not valid"))
            }
        }
    }

    private fun checkConstrainsActivate() {
        val isActivate = this.user.active
        if (isActivate == null) {
            this.validationHandler().append(Error("'isActive' should not be null"))
        }
    }
}

