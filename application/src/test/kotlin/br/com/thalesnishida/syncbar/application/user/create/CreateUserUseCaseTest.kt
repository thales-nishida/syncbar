package br.com.thalesnishida.syncbar.application.user.create

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import user.UserGateway

class CreateUserUseCaseTest {

    @Test
    fun givenAValidParams_whenCallCreateUser_thenReturnSuccess() {
        val expectName = "Test Test"
        val expectEmail = "test@test.com"
        val expectPassword = "testdaaq12@"
        val expectTypeUser = "ADMIN"
        val expectActivate = true

        val aCommand = CreateUserCommand.with(expectName, expectEmail, expectPassword, expectTypeUser, expectActivate)

        val userGateway: UserGateway = mockk()

        every { userGateway.create(any()) } answers { firstArg() }

        val useCase = DefaultCreateUserUseCase(userGateway)

        val actualOutput = useCase.execute(aCommand)

        Assertions.assertNotNull(actualOutput)
        Assertions.assertNotNull(actualOutput.userId)

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

}