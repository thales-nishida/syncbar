package br.com.thalesnishida.syncbar.application.user.update

import arrow.core.Either
import exceptions.DomainException
import user.User
import user.UserGateway
import validation.handler.Notification
import java.util.function.Supplier

class DefaultUpdateUserUseCase(private val userGateway: UserGateway) : UpdateUserUseCase() {
    override fun execute(aCommand: UpdateUserCommand): Either<Notification, UpdateUserOutput> {
        val anId = aCommand.id
        val aName = aCommand.name
        val aEmail = aCommand.email
        val aPassword = aCommand.password
        val aTypeUser = aCommand.typeUser
        val aIsActivated = aCommand.isActivated

        val aUser = this.userGateway.findById(anId)
            .orElseThrow(Supplier { DomainException.with(validation.Error("User with ID $anId not found")) })

        val notification: Notification = Notification().create()
        aUser.update(aName, aEmail, aPassword, aTypeUser, aIsActivated).validate(notification)

        return if (notification.hasErrors()) {
            Either.Left(notification)
        } else {
            update(aUser)
        }
    }

    private fun update(aUser: User): Either<Notification, UpdateUserOutput> {
        return Either.catch { userGateway.update(aUser) }
            .mapLeft { throwable -> Notification().create(throwable) }
            .map { user -> UpdateUserOutput.from(user) }
    }
}