package fr.miage.toulouse.m2.ams.miagebankmonothspring.repo;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Compte;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class CompteRepositoryTest {

    @Autowired
    CompteRepository compteRepository;

    @BeforeEach
    void setUp() {
        assertNotNull(compteRepository);
        compteRepository.deleteAll();
        Compte c = new Compte(0, 1000, 0L);
        compteRepository.save(c);
    }

    @Test
    void findAllByIdclient() {
        assertNotNull(compteRepository);
        List<Compte> compteList = compteRepository.findAllByIdclient(0L);
        assertNotNull(compteList);
        assertEquals(compteList.size(),1);
        Compte compte = compteList.get(0);
        assertEquals(compte.getIdclient(), 0L);
        assertEquals(compte.getSolde(), 1000, 0.1);
        assertEquals(compte.getId(), 0);
    }
}