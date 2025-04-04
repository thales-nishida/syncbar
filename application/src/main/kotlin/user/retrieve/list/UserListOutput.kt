package br.com.thalesnishida.syncbar.application.user.retrieve.list

import user.User
import user.UserID
import java.time.Instant

data class UserListOutput(
    val anId: UserID,
    var aName: String?,
    var aEmail: String?,
    var aTypeUser: String?,
    var active: Boolean?,
    val createdAt: Instant,
    var deletedAt: Instant?,
) {
    companion object {
        fun from(aUser: User): UserListOutput {
            return UserListOutput(
                anId = aUser.anId,
                aName = aUser.aName,
                aEmail = aUser.aEmail,
                aTypeUser = aUser.aTypeUser,
                active = aUser.active,
                createdAt = aUser.createdAt,
                deletedAt = aUser.deletedAt,
            )
        }
    }
}