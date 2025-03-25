package br.com.thalesnishida.syncbar.application

import br.com.thalesnishida.syncbar.domain.Barber
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class UseCaseTest {

    @Test
    fun `testUseCase`(){
        Assertions.assertNotNull(UseCase())
        Assertions.assertNotNull(UseCase().execute())
    }
}