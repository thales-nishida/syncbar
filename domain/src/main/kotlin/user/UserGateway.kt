package user

import pagination.Pagination
import java.util.*

interface UserGateway {
    fun create(user: User): User
    fun deleteById(anId: UserID): User
    fun findById(anId: UserID): Optional<User>
    fun update(anUpdate: User): User
    fun fidAll(query: UserSearchQuery): Pagination<User>
}