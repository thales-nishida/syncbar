package br.com.thalesnishida.syncbar.application

abstract class NullaryUseCase<OUT> {
    abstract fun execute(): OUT
}