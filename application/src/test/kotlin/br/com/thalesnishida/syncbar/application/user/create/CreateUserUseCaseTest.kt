package br.com.thalesnishida.syncbar.application.user.create

import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import user.UserGateway

class CreateUserUseCaseTest {

    @MockK
    private lateinit var userGateway: UserGateway

    @InjectMockKs
    private lateinit var useCase: DefaultCreateUserUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        clearMocks(userGateway)
    }

    @Test
    fun givenAValidParams_whenCallCreateUser_thenReturnSuccess() {
        val expectName = "Test Test"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectTypeUser, expectActivate)

        every { userGateway.create(any()) } answers { firstArg() }

        val actualOutput = useCase.execute(aCommand).getOrNone()

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.getOrNull()?.userId)

        verify(exactly = 1) {
            userGateway.create(match { aUser ->
                expectName == aUser.aName &&
                        expectEmail == aUser.aEmail &&
                        expectTypeUser == aUser.aTypeUser &&
                        expectActivate == aUser.active
            })
        }
    }

    @Test
    fun givenAInvalidParamsNameLess3Character_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = "2"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "'name' must between 3 characters and 255 characters"

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectTypeUser, expectActivate)

        val notification = useCase.execute(aCommand).leftOrNull()
        Assertions.assertEquals(exceptErrorMessage, notification?.firstError()?.message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

    @Test
    fun givenAInvalidParamsNullName_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = null
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "'name' should not be null"
        val exceptErrorCount = 1

        val aCommand = CreateUserCommand.with(
            aName = expectName,
            aEmail = expectEmail,
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
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "'email' should not be null"

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectTypeUser, expectActivate)

        val notification = useCase.execute(aCommand).leftOrNull()
        Assertions.assertEquals(exceptErrorMessage, notification!!.getErrors()[0].message)

        verify(exactly = 0) { userGateway.create(any()) }
    }


    @Test
    fun givenAInvalidParamsNullTypeUser_whenCallsCreateUser_thenReturnDomainException() {
        val expectName = "Test Test"
        val expectEmail = "teste@test.com"
        val expectTypeUser = null
        val expectActivate = true
        val exceptErrorMessage = "'typeUser' should not be null"

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectTypeUser, expectActivate)

        val notification = useCase.execute(aCommand).leftOrNull()
        Assertions.assertEquals(exceptErrorMessage, notification!!.firstError()?.message)

        verify(exactly = 0) { userGateway.create(any()) }
    }

    @Test
    fun givenAInvalidParamsNameLess3Character_whenCallGetawayThrows_thenReturnAException() {
        val expectName = "Test Test"
        val expectEmail = "test@test.com"
        val expectTypeUser = "ADMIN"
        val expectActivate = true
        val exceptErrorMessage = "Gateway error"
        val exceptErrorCount = 1

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectTypeUser, expectActivate)

        every { userGateway.create(any()) } throws IllegalStateException(exceptErrorMessage)

        val notification = useCase.execute(aCommand).leftOrNull()

        Assertions.assertEquals(exceptErrorCount, notification!!.getErrors().size)
        Assertions.assertEquals(exceptErrorMessage, notification.firstError()?.message)

        verify(exactly = 1) { userGateway.create(any()) }
    }

}