package validation


interface ValidationHandler {
    fun append(anError: Error) : ValidationHandler
    fun append(anHandler: ValidationHandler) : ValidationHandler
    fun validate(aValidation: Validation) : ValidationHandler

    fun getErrors() : List<Error>

    fun hasErrors(): Boolean {
        return getErrors().isNotEmpty()
    }

    fun firstError() : Error? {
        return if(getErrors().isNotEmpty()) {
            getErrors().first()
        } else {
            null
        }
    }

    interface Validation {
        fun validate() : Void
    }
}