package br.com.thalesnishida.syncbar.application.user.update

import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import user.User
import user.UserGateway
import java.util.*

class UpdateUserUseCaseTest {

    @MockK
    private lateinit var userGateway: UserGateway

    @InjectMockKs
    private lateinit var useCase: DefaultUpdateUserUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        clearMocks(userGateway)
    }

    @Test
    fun givenAValidParams_whenCallsUpdateUser_thenShouldReturnUserId() {
        val aUser = User.newUser(
            name = "Test2",
            email = "test2@test.com2",
            typeUser = "ADMIN",
            password = "test2@test.com2",
            isActivate = true
        )

        val expectId = aUser.anId
        val expectName = "Test Update"
        val expectEmail = "update@test.com"
        val expectPassword = "updateddd@das"
        val expectType = "CLIENT"
        val expectActivate = false

        val aCommand = UpdateUserCommand.with(
            anId = expectId,
            aName = expectName,
            aEmail = expectEmail,
            aTypeUser = expectType,
            aPassword = expectPassword,
            aIsActivate = expectActivate
        )

        every { userGateway.findById(eq(expectId)) } returns Optional.of(aUser)
        every { userGateway.update(any()) } answers { firstArg() }

        val actualOutput = useCase.execute(aCommand).getOrNone()

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.getOrNull()?.userId)

        verify(exactly = 1) { userGateway.findById(eq(expectId)) }
        verify(exactly = 1) {
            userGateway.update(withArg { aUpdateThat ->
                Objects.equals(expectId, aUpdateThat.anId)
                Objects.equals(expectName, aUpdateThat.aName)
                Objects.equals(expectEmail, aUpdateThat.aEmail)
                Objects.equals(expectType, aUpdateThat.aTypeUser)
                aUser.updatedAt.isBefore(aUpdateThat.updatedAt)
                Objects.equals(expectActivate, aUpdateThat.active)
            })
        }
    }

    @Test
    fun givenAInvalidName_whenCallsUpdateUser_shouldReturnDomainException() {
        val aUser = User.newUser(
            name = "Test",
            email = "test@test.com",
            password = "test2@test.com",
            typeUser = "ADMIN",
            isActivate = true
        )

        val expectId = aUser.anId
        val expectName = null
        val expectEmail = "test@test.com"
        val expectType = "CLIENT"
        val exceptPassword = "tesqwqqt@das"
        val exceptErrorMessage = "'name' should not be null"
        val exceptErrorCount = 1
        val expectActivate = true

        val aCommand = UpdateUserCommand.with(
            anId = expectId,
            aName = expectName,
            aEmail = expectEmail,
            aTypeUser = expectType,
            aPassword = exceptPassword,
        )

        every { userGateway.findById(eq(expectId)) } returns Optional.of(aUser)
        every { userGateway.update(any()) } answers { firstArg() }

        val actualOutput = useCase.execute(aCommand).leftOrNull()

        Assertions.assertEquals(exceptErrorMessage, actualOutput?.firstError()?.message)
        Assertions.assertEquals(exceptErrorCount, actualOutput?.getErrors()?.size)

        verify(exactly = 1) { userGateway.findById(eq(expectId)) }
    }

    @Test
    fun givenAValidInactivateCommand_whenCallsUpdateUser_shouldReturnInactivateUser() {
        val aUser = User.newUser(
            name = "Test",
            email = "test@test.com",
            password = "test2@test.com",
            typeUser = "ADMIN",
            isActivate = true
        )

        val expectId = aUser.anId
        val expectName = "Test 2"
        val expectEmail = "test@test.com"
        val expectType = "CLIENT"
        val exceptPassword = "tesqwqqt@das"
        val exceptActivate = false

        val aCommand = UpdateUserCommand.with(
            anId = expectId,
            aName = expectName,
            aEmail = expectEmail,
            aTypeUser = expectType,
            aPassword = exceptPassword,
            aIsActivate = exceptActivate
        )

        Assertions.assertTrue(aUser.active!!)
        Assertions.assertNull(aUser.deletedAt)

        every { userGateway.findById(eq(expectId)) } returns Optional.of(aUser)
        every { userGateway.update(any()) } answers { firstArg() }

        val actualOutput = useCase.execute(aCommand).getOrNone()

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.getOrNull()?.userId)

        verify(exactly = 1) { userGateway.findById(eq(expectId)) }
        verify(exactly = 1) {
            userGateway.update(withArg { aUpdateThat ->
                Objects.equals(expectId, aUpdateThat.anId)
                Objects.equals(expectName, aUpdateThat.aName)
                Objects.equals(expectEmail, aUpdateThat.aEmail)
                Objects.equals(expectType, aUpdateThat.aTypeUser)
                aUser.updatedAt.isBefore(aUpdateThat.updatedAt)
                Objects.equals(exceptActivate, aUpdateThat.active)
            })
        }
    }

    @Test
    fun givenAInvalidParams_whenCallUserGateway_thenShouldReturnErrorMessage() {
        val aUser = User.newUser(
            name = "Test",
            email = "test@test.com",
            password = "test2@test.com",
            typeUser = "ADMIN",
            isActivate = true
        )

        val expectId = aUser.anId
        val expectName = "Test"
        val expectEmail = "test@test.com"
        val expectType = "CLIENT"
        val exceptPassword = "tesqwqqt@das"
        val exceptErrorCount = 1
        val exceptErrorMessage = "Gateway Error"

        val aCommand = UpdateUserCommand.with(
            anId = expectId,
            aName = expectName,
            aEmail = expectEmail,
            aTypeUser = expectType,
            aPassword = exceptPassword
        )

        every { userGateway.findById(eq(expectId)) } returns Optional.of(aUser)
        every { userGateway.update(any()) } throws IllegalStateException(exceptErrorMessage)


        val notification = useCase.execute(aCommand).leftOrNull()

        Assertions.assertEquals(exceptErrorCount, notification?.getErrors()?.size)
        Assertions.assertEquals(exceptErrorMessage, notification?.getErrors()?.get(0)?.message)

        verify(exactly = 1) { userGateway.update(any()) }
    }
}