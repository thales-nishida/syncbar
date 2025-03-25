package br.com.thalesnishida.syncbar.application

import br.com.thalesnishida.syncbar.domain.Barber

class UseCase {

    fun execute() : Barber {
        return Barber()
    }
}