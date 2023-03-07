package fr.miage.toulouse.m2.ams.miagebankmonothspring.metier;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Compte;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.export.ClientWithCompte;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.ClientRepository;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.CompteRepository;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.utilities.ClientInexistant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de test pour ClientsCompte
 *
 * Ici, c'est un test d'intégration avec une BD H2 en mémoire et la base test de mongo
 * Note : ces tests ne fonctionnent que via Maven.
 */
@SpringBootTest
class ClientsCompteTest {

    @Autowired
    ClientRepository clientRepository; // sera injecté par SpringBootTest

    @Autowired
    CompteRepository compteRepository; // sera injecté par SpringBootTest

    ClientsCompte clientsCompte; // ce qu'on teste

    /**
     * Setup du client et du compte
     */
    @BeforeEach
    void setUp() {
        // on vérifie qu'on a bien le clientRepository
        assertNotNull(clientRepository);
        // on crée un client
        Client client = new Client();
        client.setId(0L);
        client.setPrenom("Jean");
        client.setNom("Dupond");
        // on le sauve en base
        clientRepository.save(client);
        // on vérifie qu'on a bien le compteRepository
        assertNotNull(compteRepository);
        // on efface tous les comptes de la base mongo test
        compteRepository.deleteAll();
        // création du compte
        Compte c = new Compte(0, 1000, 0L);
        // sauvegarde en base test
        compteRepository.save(c);
        // création du bean à tester
        clientsCompte = new ClientsCompteImpl(clientRepository, compteRepository);
    }

    /**
     * Test de la méthode getClientWithComptes
     */
    @Test
    void getClientWithComptes() {
        // on appelle la méthode
        ClientWithCompte clientWithCompte = clientsCompte.getClientWithComptes(0L);
        // on vérifie qu'on quelque chose de correct
        assertNotNull(clientWithCompte);
        assertEquals(clientWithCompte.getId(), 0L);
        assertEquals(clientWithCompte.getNom(), "Dupond");
        assertEquals(clientWithCompte.getPrenom(), "Jean");
        // on vérifie qu'il y a bien un compte
        assertEquals(clientWithCompte.getComptes().size(), 1);
        Compte compte = clientWithCompte.getComptes().get(0);
        // on vérifie qu'il est correct
        assertEquals(compte.getIdclient(), 0L);
        assertEquals(compte.getSolde(), 1000, 0.1);
        assertEquals(compte.getId(), 0);
        // on vérifie que ça jette une exception si on demande un client inexistant
        assertThrows(ClientInexistant.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                // on appelle la méthode
                ClientWithCompte clientWithCompte = clientsCompte.getClientWithComptes(1L);
            }
        });
    }
}