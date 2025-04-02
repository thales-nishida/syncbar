package br.com.thalesnishida.syncbar.application.user.update

import arrow.core.Either
import exceptions.DomainException
import user.User
import user.UserGateway
import validation.handler.Notification
import java.util.function.Supplier

class DefaultUpdateUserUseCase(private val userGateway: UserGateway) : UpdateUserUseCase() {
    override fun execute(anIn: UpdateUserCommand): Either<Notification, UpdateUserOutput> {
        val anId = anIn.id
        val aName = anIn.name
        val aEmail = anIn.email
        val aTypeUser = anIn.typeUser
        val aIsActive = anIn.isActivated

        val aUser = this.userGateway.findById(anId)
            .orElseThrow(Supplier { DomainException.with(validation.Error("User with ID $anId not found")) })

        val notification: Notification = Notification().create()
        aUser.update(aName, aEmail, aTypeUser, aIsActive).validate(notification)

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