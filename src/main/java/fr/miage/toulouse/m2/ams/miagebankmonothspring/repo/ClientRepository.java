package fr.miage.toulouse.m2.ams.miagebankmonothspring.repo;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface ClientRepository extends CrudRepository<Client, Long> {
}