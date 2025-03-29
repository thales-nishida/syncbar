package validation.handler

import exceptions.DomainException
import validation.Error
import validation.ValidationHandler

class Notification(private val errors: MutableList<Error> = mutableListOf()) : ValidationHandler {

    fun create(): Notification {
        return Notification(ArrayList())
    }

    fun create(t: Throwable): Notification {
        val notification = create()
        notification.errors.add(Error(t.message ?: "Unknown error"))
        return notification
    }

    fun create(anError: Error): Notification {
        return Notification(ArrayList()).append(anError)
    }

    override fun append(anError: Error): Notification {
        this.errors.add(anError)
        return this
    }

    override fun append(anHandler: ValidationHandler): Notification {
        this.errors.addAll(anHandler.getErrors())
        return this
    }

    override fun validate(aValidation: ValidationHandler.Validation): Notification {
        try {
            aValidation.validate()
        } catch (e: DomainException) {
            this.errors.addAll(e.getErrors())
        } catch (t: Throwable) {
            this.errors.add(Error(t.message.toString()))
        }
        return this
    }

    override fun getErrors(): List<Error> {
        return this.errors
    }
}