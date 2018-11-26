package ru.alvisid.pacs.util.exceptions;

/**
 * The exception is thrown when enum loader cannot syncs enums
 * end dictionaries in the data base.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class EnumLoaderException extends RuntimeException {
    /**
     * onstructs a new enum loader exception with the specified detail message.
     *
     * @param message the specified detail message.
     * @see RuntimeException#RuntimeException(String)
     */
    public EnumLoaderException(String message) {
        super(message);
    }

    /**
     * Constructs a new runtime exception with the specified detail message and
     * cause.
     *
     * @param message the detail message.
     * @param cause   the cause.
     * @see RuntimeException#RuntimeException(String, Throwable)
     */
    public EnumLoaderException(String message, Throwable cause) {
        super(message, cause);
    }
}
