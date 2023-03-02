package fr.miage.toulouse.m2.ams.miagebankmonothspring.repo;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Compte;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Repository de gestion des comptes en banque
 */
@Component
public interface CompteRepository extends MongoRepository<Compte,Long> {

    List<Compte> findAllByIdclient(Long idclient);
}
