package com.smartdevs.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 * Created by johnnym on 30/05/15.
 */
public class InputLengthException extends WebApplicationException {
    public InputLengthException(String msg) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(msg).build());
    }
}
