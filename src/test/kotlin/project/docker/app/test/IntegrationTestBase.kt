package project.docker.app.test

import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("test")
abstract class IntegrationTestBase {

    @BeforeEach
    fun setUp() {
        dbInitialization.truncateAllEntity()
    }

    @Autowired
    private lateinit var dbInitialization: DatabaseInitialization
}
