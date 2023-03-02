package fr.miage.toulouse.m2.ams.miagebankmonothspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = MiageBankMonothSpringApplication.class)
@AutoConfigureMockMvc
@AutoConfigureDataMongo
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
class MiageBankMonothSpringApplicationTests {

    @Test
    void contextLoads() {
    }

}
