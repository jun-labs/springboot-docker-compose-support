package project.docker.app.test.user.integrationtest

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.jdbc.Sql
import project.docker.app.core.user.application.UserSaveUseCase
import project.docker.app.core.user.application.UserSearchUseCase
import project.docker.app.core.user.application.service.UserNotFoundException
import project.docker.app.core.user.domain.User
import project.docker.app.test.IntegrationTestBase

@Sql(value = ["classpath:sql/schema.sql"])
class UserSearchIntegrationTest : IntegrationTestBase() {

    @Autowired
    private lateinit var userSaveUseCase: UserSaveUseCase

    @Autowired
    private lateinit var userSearchUseCase: UserSearchUseCase

    @Test
    @DisplayName("사용자가 존재하면 Id로 조회할 수 있다.")
    fun userSearchTest() {
        val newUser = userSaveUseCase.save(User("Hello"))
        val findUser = userSearchUseCase.findById(newUser.id!!)

        assertNotNull(findUser)
    }

    @Test
    @DisplayName("사용자가 존재하지 않으면 UserNotFoundException이 발생한다.")
    fun userSearchNotFoundTest() {
        assertThatThrownBy { userSearchUseCase.findById(Long.MAX_VALUE) }
            .isInstanceOf(RuntimeException::class.java)
            .isExactlyInstanceOf(UserNotFoundException::class.java)
    }
}
