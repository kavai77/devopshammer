package com.smartdevs.service;

import com.google.inject.Inject;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.engine.SamlDecoder;
import com.smartdevs.engine.SamlEncoder;
import com.smartdevs.entity.SamlResponse;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("saml")
public class SamlService {
    private static final SamlResponse EMPTY_RESPONSE = new SamlResponse();

    @Inject
    SamlEncoder samlEncoder;
    @Inject
    SamlDecoder samlDecoder;

    @POST
    @Path("decode/{format}")
    @Produces(MediaType.APPLICATION_XML)
    @MaxInputLengthValidator
    public SamlResponse decode(String saml, @PathParam( "format" ) String format) {
        if (StringUtils.isBlank(saml) || StringUtils.isBlank( format )) {
            return EMPTY_RESPONSE;
        }
        try {
            return samlDecoder.decodeSamlRequest(saml, SamlResponse.BindingFormat.valueOf(format.toUpperCase()));
        } catch ( IllegalArgumentException e ) {
            return EMPTY_RESPONSE;
        }
    }

    @POST
    @Path("encode/{format}")
    @Produces(MediaType.APPLICATION_XML)
    @MaxInputLengthValidator
    public SamlResponse encode(String saml, @PathParam( "format" ) String format) {
        if (StringUtils.isBlank(saml) || StringUtils.isBlank( format )) {
            return EMPTY_RESPONSE;
        }
        try {
            return samlEncoder.encodeSamlRequest(saml, SamlResponse.BindingFormat.valueOf(format.toUpperCase()));
        } catch ( IllegalArgumentException e ) {
            return EMPTY_RESPONSE;
        }
    }
}
