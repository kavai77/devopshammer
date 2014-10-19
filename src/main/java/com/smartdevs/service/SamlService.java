package com.smartdevs.service;

import com.google.inject.Inject;
import com.smartdevs.engine.SamlDecoder;
import com.smartdevs.engine.SamlEncoder;
import com.smartdevs.entity.SamlResponse;
import org.apache.commons.lang.StringUtils;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    @Path("decode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SamlResponse decode(String saml) {
        if (StringUtils.isBlank(saml)) {
            return EMPTY_RESPONSE;
        }
        return samlDecoder.decodeSamlRequest(saml);
    }

    @POST
    @Path("encode")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public SamlResponse encode(String saml) {
        if (StringUtils.isBlank(saml)) {
            return EMPTY_RESPONSE;
        }
        return samlEncoder.encodeSamlRequest(saml);
    }
}
