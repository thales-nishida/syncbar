package br.com.thalesnishida.syncbar.application.user.retrieve.get

import exceptions.DomainException
import user.UserGateway
import user.UserID
import validation.Error
import java.util.function.Supplier

    class DefaultGetUserByIdUseCase(private val userGateway: UserGateway) : UserGetUserByIdUseCase() {

        override fun execute(anIn: String): UserOutput {
            val anId = UserID.from(anIn)
            return this.userGateway.findById(anId)
                .map(UserOutput::from)
                .orElseThrow(Supplier { DomainException.with(Error("User with ID $anId not found")) })
        }
    }