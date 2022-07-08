package main.exceptions;

public class WrongDirectoryContentException extends RuntimeException {

    public WrongDirectoryContentException(String message) {
        System.err.println(message);
    }
}
