package com.assignment.exception;


/**
 * Exception thrown when validation failed or other cases
 */
public class InputConstraintsViolatedException extends RuntimeException {

    private String message;

    public InputConstraintsViolatedException(String message) {
        super(message);
        this.message = message;
    }

    public InputConstraintsViolatedException(Throwable cause) {
        super(cause);
    }

}