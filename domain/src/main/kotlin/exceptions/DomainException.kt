package exceptions

import validation.Error

class DomainException(private val anErrors: List<Error>) : NoStacktraceException() {

    companion object {

        fun with(anErrors: Error): DomainException {
            return DomainException(listOf(anErrors))
        }

        fun with(anErrors: List<Error>): DomainException {
            return DomainException(anErrors)
        }
    }

    fun getErrors(): List<Error> = anErrors

}