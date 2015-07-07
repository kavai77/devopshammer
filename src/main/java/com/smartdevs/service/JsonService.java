package com.smartdevs.service;

import com.google.inject.Inject;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.engine.PrettyJsonPrinter;
import com.smartdevs.exception.BadRequestException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by coby on 05/04/15.
 */
@Path("json")
public class JsonService {
    @Inject
    PrettyJsonPrinter prettyJsonPrinter;

    @POST
    @Path("pretty")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @MaxInputLengthValidator
    public String decode(String json) throws BadRequestException {
        return prettyJsonPrinter.getPrettyJson(json);
    }
}