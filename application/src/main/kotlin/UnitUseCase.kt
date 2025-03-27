package br.com.thalesnishida.syncbar.application

abstract class UnitUseCase<IN> {
    abstract fun execute(anIn: IN)
}