package fr.miage.toulouse.m2.ams.miagebankmonothspring.utilities;

/**
 * Exception compte inconnu
 */
public class CompteInconnuException extends RuntimeException {
    /**
     * Constructeur
     * @param s message d'erreur
     */
    public CompteInconnuException(String s) {
        super(s);
    }
}
