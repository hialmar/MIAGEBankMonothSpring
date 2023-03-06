package fr.miage.toulouse.m2.ams.miagebankmonothspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * Tests créés à partir de https://www.baeldung.com/spring-boot-testing
 * Attention ici on est resté avec Junit5 donc les configurations sont un peu différentes
 *
 * Note : la plupart de ces tests ne fonctionnent que via Maven puisqu'ils utilisent le plugin Spring Boot
 */

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MiageBankMonothSpringApplication.class)
@AutoConfigureMockMvc
@AutoConfigureDataMongo
class MiageBankMonothSpringApplicationTests {

    @Test
    void contextLoads() {
    }

}
