package fr.miage.toulouse.m2.ams.miagebankmonothspring.repo;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test d'intégration du repository des clients
 *
 * Utilise une BD H2 en mémoire
 */
@DataJpaTest
class ClientRepositoryTest {

    // sera injecté par @DataJpaTest
    @Autowired
    ClientRepository clientRepository;

    /**
     * Crée un client et le sauve en base
     */
    @BeforeEach
    void setUp() {
        // on vérifie qu'on a bien le repo
        assertNotNull(clientRepository);
        // on crée le client
        Client client = new Client();
        client.setId(0L);
        client.setPrenom("Jean");
        client.setNom("Dupond");
        // on le sauve en base
        clientRepository.save(client);
    }

    /**
     * On vérifie que la méthode findAll marche
     */
    @Test
    void findAll() {
        // on appelle la méthode
        Iterable<Client> clients = clientRepository.findAll();
        // on vérifie qu'elle renvoie des choses logiques
        assertNotNull(clients);
        // il y a un client
        assertTrue(clients.iterator().hasNext());
        // le client est correct
        Client client = clients.iterator().next();
        assertEquals(client.getId(), 0L);
        assertEquals(client.getPrenom(), "Jean");
        assertEquals(client.getNom(), "Dupond");
    }

}