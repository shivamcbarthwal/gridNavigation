package com.gridnavigation.app.exceptions;

/**
 * This exception is thrown when an invalid movement format is encountered.
 * It is a subclass of the Exception class.
 */
public class InvalidMovementFormatException extends Exception {
    /**
     * Constructs a new InvalidMovementFormatException with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public InvalidMovementFormatException(String message) {
        super(message);
    }
}
