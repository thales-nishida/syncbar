package validation


interface ValidationHandler {
    fun append(anError: Error) : ValidationHandler
    fun append(anHandler: ValidationHandler) : ValidationHandler
    fun validate(aValidation: Validation) : ValidationHandler

    fun getErrors() : List<Error>

    fun hasErrors(): Boolean {
        return getErrors().isNotEmpty()
    }

    interface Validation {
        fun validate() : Void
    }
}