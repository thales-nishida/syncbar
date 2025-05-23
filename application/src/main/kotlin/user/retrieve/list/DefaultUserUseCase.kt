package br.com.thalesnishida.syncbar.application.user.retrieve.list

import pagination.Pagination
import user.UserGateway
import user.UserSearchQuery

class DefaultUserUseCase(private val userGateway: UserGateway) : ListUsersUseCase() {
    override fun execute(anIn: UserSearchQuery): Pagination<UserListOutput> {
        return this.userGateway.findAll(anIn).map(UserListOutput::from)
    }
}