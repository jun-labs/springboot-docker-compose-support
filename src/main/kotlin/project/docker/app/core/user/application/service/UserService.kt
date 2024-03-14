package project.docker.app.core.user.application.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import project.docker.app.core.user.application.UserSaveUseCase
import project.docker.app.core.user.application.UserSearchUseCase
import project.docker.app.core.user.domain.User
import project.docker.app.core.user.domain.UserJpaRepository

@Service
class UserService(
    private val userRepository: UserJpaRepository
) : UserSaveUseCase, UserSearchUseCase {

    @Transactional
    override fun save(user: User): User {
        return userRepository.save(user)
    }

    @Transactional(readOnly = true)
    override fun findById(id: Long): User {
        return userRepository.findById(id)
            .orElseThrow { UserNotFoundException() }
    }
}

class UserNotFoundException : RuntimeException()
