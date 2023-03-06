package fr.miage.toulouse.m2.ams.miagebankmonothspring.exposition;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Classe de test unitaire pour le contrôleur REST ClientController
 */
@WebMvcTest(ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mvc; // injecté automatiquement

    // Le repository est mocké
    @MockBean
    ClientRepository clientRepository;

    ClientController clientController;

    Client client;

    /**
     * Setup du mock et création du controller
     */
    @BeforeEach
    void setUp() {
        assertNotNull(clientRepository);
        clientController = new ClientController(clientRepository);
        client = new Client();
        client.setId(0L);
        client.setPrenom("Jean");
        client.setNom("Dupond");
        given(clientRepository.findById(0L)).willReturn(Optional.of(client));
        List<Client> clientList = Arrays.asList(client);
        given(clientRepository.findAll()).willReturn(clientList);
        given(clientRepository.save(any())).willReturn(client);
    }

    /**
     * Test du get d'un client
     * @throws Exception en cas de problème avec MockMvc
     */
    @Test
    void getClient() throws Exception {
        // On appelle la méthode GET
        mvc.perform(get("/api/clients/0")
                        .contentType("application/json;charset=UTF-8")) // précise le content-type
                .andExpect(status().isOk()) // vérifie que tout s'est bien passé
                .andExpect(jsonPath("$.nom", is(client.getNom()))); // vérifie qu'il y a bien des infos

    }

    /**
     * Test du get de tous les clients
     * @throws Exception en cas de problème avec MockMvc
     */
    @Test
    void getClients() throws Exception {
        // On appelle la méthode GET
        mvc.perform(get("/api/clients")
                        .contentType("application/json;charset=UTF-8")) // précise le content-type
                .andExpect(status().isOk()) // vérifie que tout s'est bien passé
                .andExpect(jsonPath("$", hasSize(1))) // vérifie qu'il y a bien des infos dans la liste
                .andExpect(jsonPath("$[0].id", is(0))); // vérifie qu'il y a bien des infos
    }

    /**
     * Test du post
     * @throws Exception
     */
    @Test
    void postClient() throws Exception {
        // On appelle la méthode POST
        mvc.perform(post("/api/clients")
                        .contentType("application/json;charset=UTF-8") // précise le content-type
                        .content("{\"id\" : 0, \"nom\" : \"Test\", \"prenom\":\"Jean\"}")) // précise le contenu envoyé
                .andExpect(status().isOk()) // vérifie que tout s'est bien passé
                .andExpect(jsonPath("$.id", is(0))); // vérifie qu'il y a bien des infos

    }
}