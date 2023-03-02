package fr.miage.toulouse.m2.ams.miagebankmonothspring.exposition;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.export.ClientWithCompte;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.metier.ClientsCompte;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Service d'exposition REST des comptes-clients.
 * URL / exposée.
 */
@RestController
@RequestMapping("/api/clientcomptes")
public class ClientComptesController {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    // Injection DAO clients-compte
    @Autowired
    ClientsCompte clientsCompte;

    /**
     * GET 1 client AVEC la liste de ses comptes
     * @param id id du client
     * @return  converti en JSON
     */
    @GetMapping("{id}")
    public ClientWithCompte getClient(@PathVariable("id") Long id) {
        logger.info("ClientComptes : demande récup comptes d'un client avec id:{}", id);
        ClientWithCompte c = clientsCompte.getClientWithComptes(id);
        logger.info("ClientComptes : demande récup comptes client:{}", c);
        return c;
    }

}
