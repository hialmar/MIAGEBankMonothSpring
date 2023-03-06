package fr.miage.toulouse.m2.ams.miagebankmonothspring.metier;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Compte;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.export.ClientWithCompte;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.exposition.ClientController;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.exposition.CompteController;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.ClientRepository;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.CompteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

/**
 * Test unitaire de ClientsCompteUnitTest
 */
@ExtendWith(SpringExtension.class)
class ClientsCompteUnitTest {

    // on mocke les deux repositories
    @MockBean
    ClientRepository clientRepository;

    @MockBean
    CompteRepository compteRepository;

    // ce qu'on teste
    ClientsCompte clientsCompte;

    /**
     * Setup des mocks
     */
    @BeforeEach
    void setUp() {
        // création d'un client
        Client client = new Client();
        client.setId(0L);
        client.setPrenom("Jean");
        client.setNom("Dupond");
        // création d'un compte
        Compte c = new Compte(0, 1000, 0L);
        List<Compte> compteList = new ArrayList<>();
        compteList.add(c);
        // on vérifie que les mocks sont créés
        assertNotNull(clientRepository);
        assertNotNull(compteRepository);
        // on les configure
        given(clientRepository.findById(0L)).willReturn(Optional.of(client));
        given(compteRepository.findAllByIdclient(0L)).willReturn(compteList);
        // on crée le bean à tester
        clientsCompte = new ClientsCompteImpl(clientRepository, compteRepository);
    }

    /**
     * Test de la méthode getClientWithComptes
     */
    @Test
    void getClientWithComptes() {
        // on appelle la méthode
        ClientWithCompte clientWithCompte = clientsCompte.getClientWithComptes(0L);
        // on vérifie qu'on a bien reçu quelque chose de correct
        assertNotNull(clientWithCompte);
        assertEquals(clientWithCompte.getId(), 0L);
        assertEquals(clientWithCompte.getNom(), "Dupond");
        assertEquals(clientWithCompte.getPrenom(), "Jean");
        // on vérifie qu'il y a un compte
        assertEquals(clientWithCompte.getComptes().size(), 1);
        Compte compte = clientWithCompte.getComptes().get(0);
        // on vérifie les infos du compte
        assertEquals(compte.getIdclient(), 0L);
        assertEquals(compte.getSolde(), 1000, 0.1);
        assertEquals(compte.getId(), 0);
    }
}