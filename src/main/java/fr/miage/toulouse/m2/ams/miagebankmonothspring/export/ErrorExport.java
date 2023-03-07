package fr.miage.toulouse.m2.ams.miagebankmonothspring.export;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Classe représentant les erreurs en JSON
 */
@Data
@AllArgsConstructor
public class ErrorExport {
    /**
     * Message d'erreur
     */
    private final String message;
    /**
     * Type de l'exception
     */
    private final String exceptionType;
}
