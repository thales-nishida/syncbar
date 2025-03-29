package br.com.thalesnishida.syncbar.application.user.create

import arrow.core.Either
import br.com.thalesnishida.syncbar.application.UseCase
import validation.handler.Notification

abstract class CreateUserUseCase : UseCase<CreateUserCommand, Either<Notification, CreateUserOutput>>() {
}