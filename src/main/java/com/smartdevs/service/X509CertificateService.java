package com.smartdevs.service;

import com.google.inject.Inject;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.engine.X509CertificateDecoder;
import com.smartdevs.entity.X509CertificateResponse;
import com.smartdevs.exception.InputLengthException;
import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
import org.apache.commons.io.IOUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.X509Certificate;

@Path("x509")
public class X509CertificateService {
    @Inject
    X509CertificateDecoder decoder;

    @POST
    @Path("decode")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public X509CertificateResponse decode(@FormDataParam("file") InputStream fileInputStream,
                                  @FormDataParam("file") FormDataContentDisposition contentDispositionHeader) throws IOException {
        if (contentDispositionHeader.getSize() > MaxInputLengthValidator._1MB) {
            throw new InputLengthException("File size max. 1MB");
        }
        String content = IOUtils.toString(fileInputStream);
        X509Certificate x509Certificate = decoder.decodeCertificate(content);
        return new X509CertificateResponse(x509Certificate);
    }
}
