package com.smartdevs.service;

import com.google.inject.Inject;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.engine.X509CertificateDecoder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.security.cert.CertificateException;

/**
 * Created by 212418603 on 2015.06.23..
 */
@Path("x509")
public class X509CertificateService {
    @Inject
    X509CertificateDecoder decoder;

    @POST
    @Path("decode")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    @MaxInputLengthValidator
    public String decode(String rawCert) throws UnsupportedEncodingException {
        try {
            decoder.decodeCertificate(rawCert);
        }  catch (CertificateException e) {
            throw new WebApplicationException(Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build());
        }
        return null;
    }
}
