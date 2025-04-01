package br.com.thalesnishida.syncbar.application.user.delete

import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import user.User
import user.UserGateway
import user.UserID

class DeleteUserUseCaseTest {

    @MockK
    private lateinit var userGateway: UserGateway

    @InjectMockKs
    private lateinit var useCase: DefaultDeleteUserUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        clearMocks(userGateway)
    }

    @Test
    fun givenAValidId_whenCallsDeleteUser_thenShouldBeOk() {
        val aUser = User.newUser(
            name = "Test Test",
            email = "test@test.com",
            password = "test@asda",
            typeUser = "ADMIN",
            isActivate = true
        )
        val exceptId = aUser.anId

        coEvery { userGateway.deleteById(any()) } just runs

        Assertions.assertDoesNotThrow() { useCase.execute(exceptId.getValue()) }

        verify(exactly = 1) { userGateway.deleteById(any()) }
    }

    @Test
    fun givenAInvalidId_whenCallsDeleteUser_thenShouldBeOk() {
        val exceptId = UserID.from("12345")

        every { userGateway.deleteById(any()) } just runs

        Assertions.assertDoesNotThrow() { useCase.execute(exceptId.getValue()) }

        verify(exactly = 1) { userGateway.deleteById(any()) }
    }

    @Test
    fun givenAValidId_whenGatewayThrowError_thenShouldReturnException() {
        val aUser = User.newUser(
            name = "Test Test",
            email = "test@test.com",
            password = "test@asda",
            typeUser = "ADMIN",
            isActivate = true
        )
        val exceptId = aUser.anId
        val exceptErrorMessage = "Gateway Error"

        every { userGateway.deleteById(any()) } throws IllegalStateException(exceptErrorMessage)

        Assertions.assertThrows(IllegalStateException::class.java) { useCase.execute(exceptId.getValue()) }

        verify(exactly = 1) { userGateway.deleteById(any()) }
    }
}