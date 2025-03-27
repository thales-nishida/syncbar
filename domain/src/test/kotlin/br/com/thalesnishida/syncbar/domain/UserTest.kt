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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser)

        Assertions.assertNotNull(actualUser)
        Assertions.assertNotNull(actualUser.anId)
        Assertions.assertEquals(expectName, actualUser.aName)
        Assertions.assertEquals(expectEmail, actualUser.aEmail)
        Assertions.assertEquals(expectPassword, actualUser.aPassword)
        Assertions.assertEquals(expectTypeUser, actualUser.aTypeUser)
        Assertions.assertEquals(expectActivate, actualUser.isActivate)
    }

    @Test
    fun givenAnInvalidNullName_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName: String? = null
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'name' should not be null"
        val expectEmail = "test@test.com"
        val expectPassword = "test"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

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
        val expectPassword = "test@"
        val expectTypeUser = "CLIENTasda"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidPasswordWithoutSpecialCharacter_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'password' format is not valid"
        val expectEmail = "teste@test.com"
        val expectTypeUser = "ADMIN"
        val expectPassword = "test"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidPasswordLengthLess8_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'password' must between 8 characters and 255 characters"
        val expectEmail = "teste@test.com"
        val expectTypeUser = "ADMIN"
        val expectPassword = "tes!"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidPasswordLengthMore255_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = "Test"
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'password' must between 8 characters and 255 characters"
        val expectEmail = "teste@test.com"
        val expectTypeUser = "ADMIN"
        val expectPassword = """
             Caros amigos, a @mobilidade dos capitais internacionais nos obriga 
             à análise do retorno esperado a longo prazo. 2222213
             Todas estas questões, devidamente ponderadas, levantam dúvidas sobre se a 
             necessidade de renovação processual cumpre um papel essencial na sadadf.
        """.trimIndent()
        val expectActivate = true


        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAValidParams_whenCallUpdate_thenShouldReturnUserUpdate() {
        val expectName = "Test Name"
        val expectEmail = "test@test.com"
        val expectPassword = "testsdsad@"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        Assertions.assertDoesNotThrow() { actualUser.validate(ThrowsValidationHandler()) }

        val nameToUpdate = "Test Name Update"
        val emailToUpdate = "test@test.com.br"
        val passwordToUpdate = "testsdsad@"
        val typeUserToUpdate = "ADMIN"
        val deactivateUpdate = false

        val updateUser : User = actualUser.getUpdateName(nameToUpdate, emailToUpdate, passwordToUpdate,typeUserToUpdate, deactivateUpdate)

        Assertions.assertDoesNotThrow() { updateUser.validate(ThrowsValidationHandler()) }

        Assertions.assertEquals(actualUser.anId, updateUser.anId)
        Assertions.assertEquals(nameToUpdate, updateUser.aName)
        Assertions.assertEquals(emailToUpdate, updateUser.aEmail)
        Assertions.assertEquals(passwordToUpdate, updateUser.aPassword)
        Assertions.assertEquals(typeUserToUpdate, updateUser.aTypeUser)
        Assertions.assertEquals(deactivateUpdate, updateUser.isActivate)
    }


}