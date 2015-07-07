package com.smartdevs.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by coby on 20/10/14.
 */
public class BadRequestException extends WebApplicationException {
    public BadRequestException(Exception exception) {
        this(exception.getMessage());
    }

    public BadRequestException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(message).build());
    }
}