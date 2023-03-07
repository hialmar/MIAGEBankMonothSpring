package fr.miage.toulouse.m2.ams.miagebankmonothspring.exposition;

import fr.miage.toulouse.m2.ams.miagebankmonothspring.export.ErrorExport;
import fr.miage.toulouse.m2.ams.miagebankmonothspring.utilities.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Gestionnaire d'exceptions pour envoyer les bonnes erreurs HTTP
 */
@ControllerAdvice
public class GestionnaireExceptions {

    /**
     * Erreur 404 en cas de Compte Inconnu
     * @param request requête HTTP
     * @param exception exception
     * @return l'erreur 404
     */
    @ExceptionHandler(CompteInconnuException.class)
    public ResponseEntity<ErrorExport> gereAutreException(HttpServletRequest request, CompteInconnuException exception) {
        return new ResponseEntity<>(new ErrorExport(exception.getMessage(), exception.getClass().getName()), HttpStatus.NOT_FOUND);
    }

    /**
     * Erreur 404 en cas de Client Inconnu
     * @param request requête HTTP
     * @param exception exception
     * @return l'erreur 404
     */
    @ExceptionHandler(ClientInexistant.class)
    public ResponseEntity<ErrorExport> gereAutreException(HttpServletRequest request, ClientInexistant exception) {
        return new ResponseEntity<>(new ErrorExport(exception.getMessage(), exception.getClass().getName()), HttpStatus.NOT_FOUND);
    }


    /**
     * Erreur 404 en cas de Client Inconnu
     * Note : c'est cette erreur qui est générée lors de la transformation automatique
     * de l'id en Client
     * @param request requête HTTP
     * @param exception exception
     * @return l'erreur 404
     */
    @ExceptionHandler(MissingPathVariableException.class)
    public ResponseEntity<ErrorExport> gereAutreException(HttpServletRequest request, MissingPathVariableException exception) {
        return new ResponseEntity<>(new ErrorExport(exception.getMessage(), exception.getClass().getName()), HttpStatus.NOT_FOUND);
    }

    /**
     * Erreur 409 en cas de client dupliqué
     * @param request requête HTTP
     * @param exception exception
     * @return l'erreur 409
     */
    @ExceptionHandler(ClientDejaPresent.class)
    public ResponseEntity<ErrorExport> gereAutreException(HttpServletRequest request, ClientDejaPresent exception) {
        return new ResponseEntity<>(new ErrorExport(exception.getMessage(), exception.getClass().getName()), HttpStatus.CONFLICT);
    }

    /**
     * Erreur 409 en cas de client dupliqué
     * @param request requête HTTP
     * @param exception exception
     * @return l'erreur 409
     */
    @ExceptionHandler(CompteDejaPresent.class)
    public ResponseEntity<ErrorExport> gereAutreException(HttpServletRequest request, CompteDejaPresent exception) {
        return new ResponseEntity<>(new ErrorExport(exception.getMessage(), exception.getClass().getName()), HttpStatus.CONFLICT);
    }

    /**
     * Erreur 500 en cas d'autre erreur
     * @param request requête HTTP
     * @param exception exception
     * @return l'erreur 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorExport> gereAutreException(HttpServletRequest request, Exception exception) {
        return new ResponseEntity<>(new ErrorExport(exception.getMessage(), exception.getClass().getName()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
