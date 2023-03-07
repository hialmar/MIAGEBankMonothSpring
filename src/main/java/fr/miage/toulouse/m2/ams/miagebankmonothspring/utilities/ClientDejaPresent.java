package fr.miage.toulouse.m2.ams.miagebankmonothspring.utilities;

/**
 * Exception en cas de client dupliqué
 */
public class ClientDejaPresent extends RuntimeException {
    /**
     * Constructeur
     * @param s message d'erreur
     */
    public ClientDejaPresent(String s) {
        super(s);
    }
}
