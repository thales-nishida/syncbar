package br.com.thalesnishida.syncbar.application

import user.User

abstract class UseCase<IN, OUT> {
    abstract fun execute(anIn: IN): OUT
}