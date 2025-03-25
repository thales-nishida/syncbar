package br.com.thalesnishida.syncbar.application

import user.User

class UseCase {

    fun execute(): User {
        return User.newUser("John Doe", "john.doe@example.com", "password123", "ADMIN")
    }
}