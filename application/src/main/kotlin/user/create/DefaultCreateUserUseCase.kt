package br.com.thalesnishida.syncbar.application.user.create

import arrow.core.Either
import user.User
import user.UserGateway
import validation.handler.Notification

class DefaultCreateUserUseCase(private val userGateway: UserGateway) : CreateUserUseCase() {
    override fun execute(anIn: CreateUserCommand): Either<Notification, CreateUserOutput> {
        val aUser = User.newUser(anIn.name, anIn.email, anIn.password, anIn.typeUser, anIn.deactivate)

        val notification = Notification().create()
        aUser.validate(notification)

        return if (notification.hasErrors()) {
            Either.Left(notification)
        } else {
            create(aUser)
        }
    }

    private fun create(aUser: User): Either<Notification, CreateUserOutput> {
        return Either.catch { this.userGateway.create(aUser) }
            .mapLeft { throwable -> Notification().create(throwable) }
            .map {  user -> CreateUserOutput.from(user) }
    }
}