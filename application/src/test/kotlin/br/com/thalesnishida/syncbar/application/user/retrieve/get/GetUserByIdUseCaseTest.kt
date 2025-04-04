package br.com.thalesnishida.syncbar.application.user.retrieve.get

import exceptions.DomainException
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import user.User
import user.UserGateway
import user.UserID
import java.util.*

class GetUserByIdUseCaseTest {

    @MockK
    lateinit var userGateway: UserGateway

    @InjectMockKs
    lateinit var useCase: DefaultGetUserByIdUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        clearMocks(userGateway)
    }

    @Test
    fun givenAValidId_whenCallsGetUserById_thenShouldReturnAUser() {
        val aUser = User.newUser(
            name = "Test Test",
            email = "test@test.com",
            typeUser = "ADMIN",
            isActivate = true
        )
        val exceptId = aUser.anId

        every { userGateway.findById(match { it.getValue() == exceptId.getValue() }) } returns Optional.of(aUser)

        val actualOutput = useCase.execute(exceptId.getValue())

        Assertions.assertNotNull(actualOutput)
        Assertions.assertEquals(exceptId, actualOutput.id)
        Assertions.assertEquals(aUser.aName, actualOutput.name)
        Assertions.assertEquals(aUser.aEmail, actualOutput.email)
        Assertions.assertEquals(aUser.aTypeUser, actualOutput.typeUser)
        Assertions.assertEquals(aUser.active, actualOutput.active)
    }

    @Test
    fun givenAInvalidId_whenCallsGetUserById_thenShouldDomainException() {
        val expectId: UserID = UserID.from("123")
        val expectedErrorMessage = "User with ID 123 not found"

        every { userGateway.findById(match { it.getValue() == expectId.getValue() }) } returns Optional.empty()
        val actualException =
            Assertions.assertThrows(DomainException::class.java) { useCase.execute(expectId.getValue()) }

        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors()[0].message)
    }

    @Test
    fun givenAInvalidParams_whenCallUserGateway_thenShouldReturnErrorMessage() {
        val exceptedErrorMessage = "Gateway Error"
        val expectId = UserID.from("123")

        every { userGateway.findById(any()) } throws IllegalStateException(exceptedErrorMessage)

        val actualException =
            Assertions.assertThrows(IllegalStateException::class.java) { useCase.execute(expectId.getValue()) }

        Assertions.assertEquals(exceptedErrorMessage, actualException.message)
    }
}