package fr.miage.toulouse.m2.ams.miagebankmonothspring.utilities;

/**
 * Exception en cas de compte dupliqué
 */
public class CompteDejaPresent extends RuntimeException {
    /**
     * Constructeur
     * @param s message d'erreur
     */
    public CompteDejaPresent(String s) {
        super(s);
    }
}
