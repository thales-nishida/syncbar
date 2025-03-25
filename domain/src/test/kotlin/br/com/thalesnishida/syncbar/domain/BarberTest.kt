package br.com.thalesnishida.syncbar.domain

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class BarberTest {

    @Test
    fun `newBarber test`() {
        Assertions.assertNotNull(Barber())
    }
}