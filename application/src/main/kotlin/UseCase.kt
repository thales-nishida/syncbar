package br.com.thalesnishida.syncbar.application

abstract class UseCase<IN, OUT> {
    abstract fun execute(anIn: IN): OUT
}