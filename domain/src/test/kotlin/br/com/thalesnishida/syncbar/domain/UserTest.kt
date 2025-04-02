package br.com.thalesnishida.syncbar.domain

import exceptions.DomainException
import org.junit.jupiter.api.Assertions
import user.User
import validation.handler.ThrowsValidationHandler
import kotlin.test.Test

class UserTest {

    @Test
    fun givenAValidParams_whenCallUser_thenInstantiateAUser() {
        val expectName = "Test Name Update"
        val expectEmail = "test@test.com.br"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

        Assertions.assertDoesNotThrow() { actualUser.validate(ThrowsValidationHandler()) }

        Assertions.assertNotNull(actualUser)
        Assertions.assertNotNull(actualUser.anId)
        Assertions.assertEquals(expectName, actualUser.aName)
        Assertions.assertEquals(expectEmail, actualUser.aEmail)
        Assertions.assertEquals(expectTypeUser, actualUser.aTypeUser)
        Assertions.assertEquals(expectActivate, actualUser.active)
    }

    @Test
    fun givenAnInvalidNullName_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName: String? = null
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'name' should not be null"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAnInvalidEmptyName_whenCallUserAndValidated_thenShouldReturnError() {
        val expectName = " "
        val exceptionErrorCount = 1
        val exceptionErrorMessage = "'name' should not be empty"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser = " "
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser: String? = null
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

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
        val expectTypeUser = "CLIENTasda"
        val expectActivate = true

        val actualUser = User.newUser(expectName, expectEmail, expectTypeUser, expectActivate)

        val actualException =
            Assertions.assertThrows(DomainException::class.java) { actualUser.validate(ThrowsValidationHandler()) }
        Assertions.assertEquals(exceptionErrorMessage, actualException.getErrors()[0].message)
        Assertions.assertEquals(exceptionErrorCount, actualException.getErrors().size)
    }

    @Test
    fun givenAValidParams_whenCallUpdate_thenShouldReturnUserUpdate() {
        val expectName = "Test Name"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val actualUser = User.newUser(
            name = expectName,
            email = expectEmail,
            typeUser = expectTypeUser,
            isActivate = expectActivate
        )

        Assertions.assertDoesNotThrow() { actualUser.validate(ThrowsValidationHandler()) }

        val nameToUpdate = "Test Name Update"
        val emailToUpdate = "test@test.com.br"
        val typeUserToUpdate = "ADMIN"
        val deactivateUpdate = true

        val updateUser: User =
            actualUser.update(nameToUpdate, emailToUpdate, typeUserToUpdate, deactivateUpdate)

        Assertions.assertDoesNotThrow() { updateUser.validate(ThrowsValidationHandler()) }

        Assertions.assertEquals(actualUser.anId, updateUser.anId)
        Assertions.assertEquals(nameToUpdate, updateUser.aName)
        Assertions.assertEquals(emailToUpdate, updateUser.aEmail)
        Assertions.assertEquals(typeUserToUpdate, updateUser.aTypeUser)
    }

    @Test
    fun givenAValidActiveUser_whenCallDeactivate_thenReturnUserInactivate() {
        val expectName = "Test Name"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = false

        val aUser = User.newUser(
            name = expectName,
            email = expectEmail,
            typeUser = expectTypeUser,
            isActivate = true
        )

        Assertions.assertDoesNotThrow() { aUser.validate(ThrowsValidationHandler()) }

        val createAt = aUser.createdAt
        val updatedAt = aUser.updatedAt

        Assertions.assertTrue(aUser.active!!)
        Assertions.assertNull(aUser.deletedAt)

        Assertions.assertDoesNotThrow() { aUser.validate(ThrowsValidationHandler()) }

        val actualUser = aUser.deactivate()

        Assertions.assertEquals(aUser.anId, actualUser.anId)
        Assertions.assertEquals(expectName, actualUser.aName)
        Assertions.assertEquals(expectEmail, actualUser.aEmail)
        Assertions.assertEquals(expectTypeUser, actualUser.aTypeUser)
        Assertions.assertEquals(expectActivate, actualUser.active)
        Assertions.assertEquals(createAt, actualUser.createdAt)
        Assertions.assertTrue(actualUser.updatedAt.isAfter(updatedAt))
        Assertions.assertNotNull(actualUser.deletedAt)
    }

    @Test
    fun givenAValidInactiveUser_whenCallActivate_thenReturnUserActive() {
        val expectName = "Test Name"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val aUser = User.newUser(
            name = expectName,
            email = expectEmail,
            typeUser = expectTypeUser,
            isActivate = false
        )

        Assertions.assertDoesNotThrow() { aUser.validate(ThrowsValidationHandler()) }

        val createAt = aUser.createdAt
        val updatedAt = aUser.updatedAt

        Assertions.assertFalse(aUser.active!!)
        Assertions.assertNull(aUser.deletedAt)

        val actualUser = aUser.activate()

        Assertions.assertDoesNotThrow() { aUser.validate(ThrowsValidationHandler()) }

        Assertions.assertEquals(aUser.anId, actualUser.anId)
        Assertions.assertEquals(expectName, actualUser.aName)
        Assertions.assertEquals(expectEmail, actualUser.aEmail)
        Assertions.assertEquals(expectTypeUser, actualUser.aTypeUser)
        Assertions.assertEquals(expectActivate, actualUser.active)
        Assertions.assertEquals(createAt, actualUser.createdAt)
        Assertions.assertTrue(actualUser.updatedAt.isAfter(updatedAt))
        Assertions.assertNull(actualUser.deletedAt)
    }

}