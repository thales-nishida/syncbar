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
        checkConstrainsPassword()
    }

    private fun checkConstrainsName() {
        val name = this.user.aName
        if (name == null) {
            this.validationHandler().append(Error("'name' should not be null"))
        }
        if (name!!.isBlank() || name.isEmpty()) {
            this.validationHandler().append(Error("'name' should not be empty"))
        }
        if (name.length <= NAME_MIN_LENGTH || name.trim().length > NAME_MAX_LENGTH) {
            this.validationHandler().append(Error("'name' must between 3 characters and 255 characters"))
        }
    }

    private fun checkConstrainsEmail() {
        val email = this.user.aEmail
        val emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$".toRegex()
        if(email == null) {
            this.validationHandler().append(Error("'email' should not be null"))
        }
        if(email!!.isBlank() || email.isEmpty()) {
            this.validationHandler().append(Error("'email' should not be empty"))
        }
        if(!email.matches(emailRegex)) {
            this.validationHandler().append(Error("'email' format is not valid"))
        }
    }

    private fun checkConstrainsTypeUser() {
        val typeUser = this.user.aTypeUser
        if(typeUser == null) {
            this.validationHandler().append(Error("'typeUser' should not be null"))
        }
        if (typeUser!!.isEmpty() || typeUser.isBlank()) {
            this.validationHandler().append(Error("'typeUser' should not be empty"))
        }
        if(typeUser != ADMIN && typeUser != EMPLOYEE && typeUser != CLIENT) {
            this.validationHandler().append(Error("'typeUser' format is not valid"))
        }
    }

    private fun checkConstrainsPassword() {
        val password = this.user.aPassword
        if(!password!!.contains("@") && !password.contains("!") && !password.contains("#")) {
            this.validationHandler().append(Error("'password' format is not valid"))
        }
        if(password.length <= MIN_LENGTH_PASSWORD || password.trim().length > NAME_MAX_LENGTH) {
            this.validationHandler().append(Error("'password' must between 8 characters and 255 characters"))
        }
    }
}

