package ru.alvisid.pacs.util.exceptions;

/**
 * The exception is thrown when an employee has no permit for this action at the control point.
 *
 * @author Glushkov Evgeniy
 * @version 1.0
 */
public class IllegalActionException extends RuntimeException {
    /**
     * Constructs a new illegal action exception with the specified detail message.
     *
     * @param message the detail message.
     */
    public IllegalActionException(String message) {
        super(message);
    }
}
