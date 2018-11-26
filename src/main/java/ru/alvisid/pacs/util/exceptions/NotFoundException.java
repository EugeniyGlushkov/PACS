package ru.alvisid.pacs.util.exceptions;

/**
 * The exception is thrown when entity is not found.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class NotFoundException extends RuntimeException {
    /**
     * Constructs a new not found exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public NotFoundException(String message) {
        super(message);
    }
}
