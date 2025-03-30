package br.com.thalesnishida.syncbar.application.user.update

import user.UserID

data class UpdateUserCommand(
    val id: UserID,
    val name: String?,
    val email: String?,
    val password: String?,
    val typeUser: String?,
    val isActivated: Boolean? = false
) {
    companion object {
        fun with(
            anId: UserID,
            aName: String? = null,
            aEmail: String? = null,
            aPassword: String? = null,
            aTypeUser: String? = null,
            aIsActivate: Boolean? = false
        ): UpdateUserCommand {
            return UpdateUserCommand(anId, aName, aEmail, aPassword, aTypeUser, aIsActivate)
        }
    }
}
