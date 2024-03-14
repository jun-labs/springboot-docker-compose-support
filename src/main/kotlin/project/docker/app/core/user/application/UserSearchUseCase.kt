package project.docker.app.core.user.application

import project.docker.app.core.user.domain.User

interface UserSearchUseCase {
    fun findById(id: Long): User
}
