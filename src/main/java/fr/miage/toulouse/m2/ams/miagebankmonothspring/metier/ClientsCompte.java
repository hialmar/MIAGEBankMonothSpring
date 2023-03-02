package fr.miage.toulouse.m2.ams.miagebankmonothspring.metier;


import fr.miage.toulouse.m2.ams.miagebankmonothspring.entities.Client;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.export.ClientWithCompte;

/**
 * Repository de manipulation des clients
 */
public interface ClientsCompte {

    ClientWithCompte getClientWithComptes(Long idclient);

}
