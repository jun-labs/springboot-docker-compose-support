package project.docker.app.core.user.application

import project.docker.app.core.user.domain.User

interface UserSaveUseCase {
    fun save(user: User): User
}
