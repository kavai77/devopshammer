package com.smartdevs.exception;

/**
 * Created by coby on 20/10/14.
 */
public class PrettyJsonCreationException extends Exception {
    public PrettyJsonCreationException(Exception message) {
        super(message);
    }

    public PrettyJsonCreationException(String message) {
        super(message);
    }

    public PrettyJsonCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}