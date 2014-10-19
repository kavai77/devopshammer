package com.smartdevs.exception;

/**
 * Created by coby on 19/10/14.
 */
public class PrettyXmlCreationException extends Exception {
    public PrettyXmlCreationException(Exception message) {
        super(message);
    }

    public PrettyXmlCreationException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
