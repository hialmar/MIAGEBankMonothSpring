package fr.miage.toulouse.m2.ams.miagebankmonothspring.exposition;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Service d'exposition REST des clients.
 * URL / exposée.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {
    Logger logger = LoggerFactory.getLogger(ClientController.class);

    // Injection DAO clients
    @Autowired
    private ClientRepository repository;

    /**
     * GET 1 client
     * @param client id du client
     * @return Client converti en JSON
     */
    @GetMapping("{id}")
    public Client getClient(@PathVariable("id") Client client) {
        logger.info("Client : demande récup d'un client avec id:{}", client.getId());
        return client;
    }

    /**
     * GET liste des clients
     * @return liste des clients en JSON. [] si aucun compte.
     */
    @GetMapping("")
    public Iterable<Client> getClients() {
        logger.info("Client : demande récup des comptes clients");
        return repository.findAll();
    }

    /**
     * POST un client
     * @param client client à ajouter (import JSON)
     * @return client ajouté
     */
    @PostMapping("")
    public Client postClient(@RequestBody Client client) {
        logger.info("Client : demande CREATION d'un client avec id:{}", client.getId());
        return repository.save(client);
    }
}
