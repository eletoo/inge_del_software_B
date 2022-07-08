package main.exceptions;

/**
 * eccezione: contenuto del file invalido
 *
 * @author Elena Tonini, Claudia Manfredi, Mattia Pavlovic
 */
public class InvalidFileContentException extends RuntimeException {
    public InvalidFileContentException(String message) {
        System.err.println(message);
    }
}
