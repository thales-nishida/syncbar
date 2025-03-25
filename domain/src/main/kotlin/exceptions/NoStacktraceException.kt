package exceptions

open class NoStacktraceException : RuntimeException {

    constructor(message: String) : super(message, null, true, false)

    constructor(message: String, cause: Throwable?) : super(message, cause, true, false)

    constructor() : super(null, null, true, false)
}