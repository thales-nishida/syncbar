package br.com.thalesnishida.syncbar.domain

import exceptions.DomainException
import org.junit.jupiter.api.Assertions
import user.User
import validation.handler.ThrowsValidationHandler
import kotlin.test.Test

class UserTest {

    @Test
    fun givenAValidParams_whenCallUser_thenInstantiateAUser() {
        val expectName = "Test Name"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        Assertions.assertNotNull(actualUser)
        Assertions.assertNotNull(actualUser.anId)
        Assertions.assertEquals(expectName, actualUser.aName)
        Assertions.assertEquals(expectEmail, actualUser.aEmail)
        Assertions.assertEquals(expectPassword, actualUser.aPassword)
        Assertions.assertEquals(expectTypeUser, actualUser.aTypeUser)
    }

    @Test
    fun givenAnInvalidNullName_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName: String? = null
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'name' should not be null"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidEmptyName_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "  "
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'name' should not be empty"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidNameLengthLess3_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Th "
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'name' must between 3 characters and 255 characters"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidNameLengthMore255_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = """
             Caros amigos, a mobilidade dos capitais internacionais nos obriga 
             à análise do retorno esperado a longo prazo. 
             Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se a 
             necessidade de renovação processual cumpre um papel essencial na sadadf.
        """.trimIndent()
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'name' must between 3 characters and 255 characters"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidNullEmail_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Thales"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'email' should not be null"
        val expectEmail: String? = null
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidEmptyEmail_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'email' should not be empty"
        val expectEmail = " "
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidFormatEmail_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'email' format is not valid"
        val expectEmail = "teste.de.erro.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidEmptyTypeUser_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'typeUser' should not be empty"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser = " "

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidNullTypeUser_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'typeUser' should not be null"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser: String? = null

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidTypeUser_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'typeUser' format is not valid"
        val expectEmail = "teste@test.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMINsss"

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }
}