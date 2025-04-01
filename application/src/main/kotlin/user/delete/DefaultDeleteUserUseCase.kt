package br.com.thalesnishida.syncbar.application.user.delete

import user.UserGateway
import user.UserID

class DefaultDeleteUserUseCase(private val userGateway: UserGateway) : DeleteUserUseCase() {

    override fun execute(anIn: String) {
        this.userGateway.deleteById(UserID.from(anIn))
    }
}