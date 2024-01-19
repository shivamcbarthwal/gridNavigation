package com.gridnavigation.app.exceptions;

/**
 * This exception is thrown when a position is out of bounds.
 * It is a subclass of the Exception class.
 */
public class OutsOfBoundsPositionException extends Exception {
    public OutsOfBoundsPositionException(String message) {
        super(message);
    }
}
