package br.com.thalesnishida.syncbar.infrastructure

import org.junit.jupiter.api.Assertions
import kotlin.test.Test

class MainTest {

    @Test
    fun `testMain`() {
        Assertions.assertNotNull(main())
        main()
    }
}