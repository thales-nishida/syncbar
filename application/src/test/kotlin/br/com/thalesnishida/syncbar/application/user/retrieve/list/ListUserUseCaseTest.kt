package br.com.thalesnishida.syncbar.application.user.retrieve.list

import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import pagination.Pagination
import user.User
import user.UserGateway
import user.UserSearchQuery

class ListUsersUseCaseTest {

    @MockK
    lateinit var userGateway: UserGateway

    @InjectMockKs
    lateinit var useCase: DefaultUserUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        clearMocks(userGateway)
    }

    @Test
    fun givenAValidQuery_whenCallsListUsers_thenShouldReturnAListUser() {
        val aUsers = listOf(
            User.newUser("Test One", "teste@one.com", "CLIENT", true,),
            User.newUser("Test Two", "teste@two.com", "CLIENT", true,),
            User.newUser("Test Three", "teste@three.com", "BARBER", true,),
            User.newUser("Test Four", "teste@four.com", "ADMIN", true,),
        )

        val expectedPage = 0
        val expectedPerPage = 10
        val expectedTerms = ""
        val expectedSort = "createdAt"
        val expectedDirection = "asc"

        val aQuery = UserSearchQuery(expectedPage, expectedPerPage, expectedTerms, expectedSort, expectedDirection)

        val expectedPagination = Pagination(expectedPage, expectedPerPage, aUsers.size.toLong(), aUsers)

        val expectedCount = 4
        val expectedResult = expectedPagination.map(UserListOutput::from)

        every { userGateway.findAll(eq(aQuery)) } returns expectedPagination

        val actualResult = useCase.execute(aQuery)

        Assertions.assertEquals(expectedCount, actualResult.items.size)
        Assertions.assertEquals(expectedResult, actualResult)
        Assertions.assertEquals(expectedPage, actualResult.page)
        Assertions.assertEquals(expectedPerPage, actualResult.perPage)
        Assertions.assertEquals(aUsers.size, actualResult.total)
    }

    @Test
    fun givenAValidQuery_whenHasNoResult_thenShouldReturnAEmptyListUser() {

    }

    @Test
    fun givenAValidQuery_whenGatewayThrowsException_thenShouldThrowException() {

    }

}