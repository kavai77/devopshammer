package com.smartdevs.service;

import com.google.inject.Inject;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.engine.PrettyJsonPrinter;
import com.smartdevs.exception.BadRequestException;
import org.apache.commons.codec.binary.Base64;

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
    @Produces(MediaType.TEXT_PLAIN)
    @MaxInputLengthValidator
    public String decode(String json) throws BadRequestException {
        return Base64.encodeBase64String(prettyJsonPrinter.getPrettyJson(json).getBytes());
    }
}