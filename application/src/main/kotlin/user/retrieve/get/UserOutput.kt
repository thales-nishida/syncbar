package br.com.thalesnishida.syncbar.application.user.retrieve.get

import user.User
import user.UserID

data class UserOutput(
    val id: UserID,
    val name: String?,
    val email: String?,
    val typeUser: String?,
    val active: Boolean?,
) {
    companion object {
        fun from (aUser: User) : UserOutput {
            return UserOutput(
                aUser.anId,
                aUser.aName,
                aUser.aEmail,
                aUser.aTypeUser,
                aUser.active
            )
        }
    }
}