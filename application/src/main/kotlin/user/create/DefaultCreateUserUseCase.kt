package br.com.thalesnishida.syncbar.application.user.create

import user.User
import user.UserGateway
import validation.handler.ThrowsValidationHandler

class DefaultCreateUserUseCase(private val userGateway: UserGateway) : CreateUserUseCase() {
    override fun execute(anIn: CreateUserCommand): CreateUserOutput {
        val aUser = User.newUser(anIn.name, anIn.email, anIn.password, anIn.typeUser, anIn.deactivate)
        aUser.validate(ThrowsValidationHandler())

        return CreateUserOutput.from(this.userGateway.create(aUser))
    }
}