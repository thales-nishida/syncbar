package validation

abstract class Validator(private val handler: ValidationHandler) {
    protected fun validationHandler(): ValidationHandler {
        return handler
    }

    abstract fun validate()
}