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

/**
 * Test d'intégration du repository des comptes
 *
 * Utilise la base test de mongo
 */
@DataMongoTest
class CompteRepositoryTest {

    // repo injecté par @DataMongoTest
    @Autowired
    CompteRepository compteRepository;

    /**
     * Mise en place d'un compte sauvé en base mongo de test
     */
    @BeforeEach
    void setUp() {
        // on vérifie qu'on a bien le repo
        assertNotNull(compteRepository);
        // on efface ce qu'il y avait avant dans la collection de la base test
        compteRepository.deleteAll();
        // on crée un compte
        Compte c = new Compte(0, 1000, 0L);
        // on le sauve dans la base test
        compteRepository.save(c);
    }

    /**
     * Test de la méthode findAllByIdclient
     */
    @Test
    void findAllByIdclient() {
        // on vérifie qu'on a le repo
        assertNotNull(compteRepository);
        // on appelle la méthode
        List<Compte> compteList = compteRepository.findAllByIdclient(0L);
        // on vérifie qu'on a bien reçu quelque chose de correct
        assertNotNull(compteList);
        // il y a un compte dans la liste
        assertEquals(compteList.size(),1);
        // on vérifie les infos du compte
        Compte compte = compteList.get(0);
        assertEquals(compte.getIdclient(), 0L);
        assertEquals(compte.getSolde(), 1000, 0.1);
        assertEquals(compte.getId(), 0);
    }
}