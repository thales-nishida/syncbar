package br.com.thalesnishida.syncbar.application.user.update

import arrow.core.Either
import br.com.thalesnishida.syncbar.application.UseCase
import validation.handler.Notification

abstract class UpdateUserUseCase : UseCase<UpdateUserCommand, Either<Notification, UpdateUserOutput>>() {
}