package br.com.thalesnishida.syncbar.application.user.create

import exceptions.DomainException
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import user.UserGateway

class CreateUserUseCaseTest {

    @MockK
    private lateinit var userGateway: UserGateway

    @InjectMockKs
    private lateinit var useCase: DefaultCreateUserUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun givenAValidParams_whenCallCreateUser_thenReturnSuccess() {
        val expectName = "Test Test"
        val expectEmail = "test@test.com"
        val expectPassword = "testdaaq12@"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        every { userGateway.create(any()) } answers { firstArg() }

        val actualOutput = useCase.execute(aCommand).getOrNone()

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.getOrNull()?.userId)

        verify(exactly = 1) {
            userGateway.create(match { aUser ->
                expectName == aUser.aName &&
                        expectPassword == aUser.aPassword &&
                        expectEmail == aUser.aEmail &&
                        expectTypeUser == aUser.aTypeUser &&
                        expectActivate == aUser.isActivate
            })
        }
    }

    @Test
    fun givenAInvalidParamsNameLess3Character_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = "2"
        val expectEmail = "test@test.com"
        val expectPassword = "testdaaq12@"
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "'name' must between 3 characters and 255 characters"

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val actualException = Assertions.assertThrows(DomainException::class.java) { useCase.execute(aCommand) }
        Assertions.assertEquals(exceptErrorMessage, actualException.getErrors()[0].message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

    @Test
    fun givenAInvalidParamsNullName_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = null
        val expectEmail = "test@test.com"
        val expectPassword = "testdaaq12@"
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "'name' should not be null"
        val exceptErrorCount = 1

        val aCommand = CreateUserCommand.with(
            aName = expectName,
            aEmail= expectEmail,
            aPassword = expectPassword,
            aTypeUser = expectTypeUser,
            aDeactivate = expectActivate
        )

        val notification = useCase.execute(aCommand).leftOrNull()
        Assertions.assertEquals(exceptErrorCount, notification?.getErrors()?.size)
        Assertions.assertEquals(exceptErrorMessage, notification!!.getErrors()[0].message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

    @Test
    fun givenAInvalidParamsNullEmail_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = "Test Test"
        val expectEmail = null
        val expectPassword = "testdaaq12@"
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "'email' should not be null"

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val notification = useCase.execute(aCommand).leftOrNull()
        Assertions.assertEquals(exceptErrorMessage, notification!!.getErrors()[0].message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

    @Test
    fun givenAInvalidParamsNullPassword_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = "Test Test"
        val expectEmail = "teste@test.com"
        val expectPassword = null
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "'password' should not be null"

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val notification = useCase.execute(aCommand).leftOrNull()
        Assertions.assertEquals(exceptErrorMessage, notification!!.firstError()?.message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

    @Test
    fun givenAInvalidParamsNullTypeUser_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = "Test Test"
        val expectEmail = "teste@test.com"
        val expectPassword = "aksnkdn@aksdl"
        val expectTypeUser = null
        val expectActivate = true
        val exceptErrorMessage = "'typeUser' should not be null"

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val notification = useCase.execute(aCommand).leftOrNull()
        Assertions.assertEquals(exceptErrorMessage, notification!!.firstError()?.message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

    @Test
    fun givenAInvalidParamsNameLess3Character_whenCallGetawayThrows_thenReturnAException() {
        val expectName = "Test Test"
        val expectEmail = "test@test.com"
        val expectPassword = "testdaaq12@"
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "Gateway error"
        val exceptErrorCount = 1

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        every { userGateway.create(any()) } throws IllegalStateException("Gateway error")

        val notification = useCase.execute(aCommand).leftOrNull()

        Assertions.assertEquals(exceptErrorCount, notification!!.getErrors().size)
        Assertions.assertEquals(exceptErrorMessage, notification.firstError()?.message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

}