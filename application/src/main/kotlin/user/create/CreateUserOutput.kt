package br.com.thalesnishida.syncbar.application.user.create

import user.User
import user.UserID

data class CreateUserOutput(
    val userId: UserID
) {
    companion object {
        fun from(
            aUser: User
        ): CreateUserOutput {
            return CreateUserOutput(aUser.anId)
        }
    }
}
