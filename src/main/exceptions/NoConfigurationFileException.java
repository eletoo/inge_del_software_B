package main.exceptions;

public class NoConfigurationFileException extends RuntimeException {

    public NoConfigurationFileException(String message) {
        System.err.println(message);
    }
}
