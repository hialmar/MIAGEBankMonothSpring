package fr.miage.toulouse.m2.ams.miagebankmonothspring.exposition;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.ClientRepository;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.utilities.ClientDejaPresent;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.utilities.ClientInexistant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

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

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    /**
     * GET 1 client
     * @param id id du client
     * @return Client converti en JSON
     */
    @GetMapping("{id}")
    public Client getClient(@PathVariable("id") long id) {
        Optional<Client> optionalClient = repository.findById(id);
        if(optionalClient.isPresent()) {
            Client client = optionalClient.get();
            logger.info("Client : demande récup d'un client avec id:{}", client.getId());
            return client;
        } else {
            logger.info("Client : non trouvé avec l'id:{}", id);
            throw new ClientInexistant("Le client d'id "+id+" n'existe pas");
        }
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
        Optional<Client> optionalClient = repository.findById(client.getId());
        if(optionalClient.isPresent()) {
            logger.info("Client : erreur compte déjà exitant avec id :{}", client.getId());
            throw new ClientDejaPresent("Le client d'id "+client.getId()+" existe déjà");
        }
        logger.info("Client : demande CREATION d'un client avec id:{}", client.getId());
        return repository.save(client);
    }
}
