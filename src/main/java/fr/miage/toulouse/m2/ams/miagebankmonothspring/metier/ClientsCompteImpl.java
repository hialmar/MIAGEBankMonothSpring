package fr.miage.toulouse.m2.ams.miagebankmonothspring.metier;


import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Compte;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.export.ClientWithCompte;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.ClientRepository;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.repo.CompteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientsCompteImpl implements ClientsCompte {
    Logger logger = LoggerFactory.getLogger(this.getClass());

   @Autowired
   ClientRepository clientrepository;

    @Autowired
    CompteRepository compterepository;

    @Override
    public ClientWithCompte getClientWithComptes(Long idclient) {
        logger.info("On a 1 demande");
        // On récupère 1 objet client
        logger.info("On envoie la demande au service client");

        Optional<Client> c = this.clientrepository.findById(idclient);
        logger.info("On a recue la réponse client : {}", c);

        // On récupère la liste des comptes pour 1 client donné
        logger.info("On envoie la demande au service compte");
        List<Compte> cpts = this.compterepository.findAllByIdclient(idclient);
        logger.info("On a recue la réponse compte : {}", c);

        // On forge la réponse
        ClientWithCompte cwc = new ClientWithCompte();
        cwc.setId(c.get().getId());
        cwc.setNom(c.get().getNom());
        cwc.setPrenom(c.get().getPrenom());
        cwc.setComptes(cpts);
        return cwc;
    }

}
