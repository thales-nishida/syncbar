package br.com.thalesnishida.syncbar.application.user.retrieve.list

import br.com.thalesnishida.syncbar.application.UseCase
import pagination.Pagination
import user.UserSearchQuery

abstract class ListUsersUseCase: UseCase<UserSearchQuery, Pagination<UserListOutput>>()