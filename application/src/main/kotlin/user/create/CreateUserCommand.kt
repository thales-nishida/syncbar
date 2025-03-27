package br.com.thalesnishida.syncbar.application.user.create

data class CreateUserCommand(
    val name: String,
    val email: String,
    val password: String,
    val typeUser: String,
    val deactivate: Boolean?
) {
    companion object {
        fun with(
            aName: String,
            aEmail: String,
            aPassword: String,
            aTypeUser: String,
            aDeactivate: Boolean?
        ) : CreateUserCommand {
            return CreateUserCommand(aName, aEmail, aPassword, aTypeUser, aDeactivate)
        }
    }
}
