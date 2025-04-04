package user

import pagination.Pagination
import java.util.*

interface UserGateway {
    fun create(user: User): User
    fun deleteById(anId: UserID)
    fun findById(anId: UserID): Optional<User>
    fun update(anUpdate: User): User
    fun findAll(query: UserSearchQuery): Pagination<User>
}