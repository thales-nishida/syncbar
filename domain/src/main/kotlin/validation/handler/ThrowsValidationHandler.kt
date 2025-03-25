package validation.handler

import exceptions.DomainException
import validation.Error
import validation.ValidationHandler
import validation.ValidationHandler.*

class ThrowsValidationHandler : ValidationHandler {
    override fun append(anError: Error): ValidationHandler {
        throw DomainException.with(anError)
    }

    override fun append(anHandler: ValidationHandler): ValidationHandler {
        throw DomainException.with(anHandler.getErrors())
    }

    override fun validate(aValidation: Validation): ValidationHandler {
        try {
            aValidation.validate()
        } catch (e: Exception) {
            throw DomainException.with(Error(e.message.toString()))
        }

        return this
    }

    override fun getErrors(): List<Error> {
        return listOf<Error>()
    }
}