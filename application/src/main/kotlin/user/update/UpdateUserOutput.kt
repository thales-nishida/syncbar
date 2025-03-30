package br.com.thalesnishida.syncbar.application.user.update

import user.User
import user.UserID

data class UpdateUserOutput(
    val userId: UserID
) {
    companion object {
        fun from(aUser: User) : UpdateUserOutput {
            return UpdateUserOutput(aUser.anId)
        }
    }
}