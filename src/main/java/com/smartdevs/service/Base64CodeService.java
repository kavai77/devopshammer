package com.smartdevs.service;

import org.apache.commons.codec.binary.Base64;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;

/**
 * Created by Kávai on 2014.10.19..
 */
@Path("base64")
public class Base64CodeService {
    @POST
    @Path("decode")
    @Produces(MediaType.TEXT_PLAIN)
    public String decode(String data) throws UnsupportedEncodingException {
        return new String(Base64.decodeBase64(data), "UTF-8");
    }

    @POST
    @Path("encode")
    @Produces(MediaType.TEXT_PLAIN)
    public String encode(String data) throws UnsupportedEncodingException {
        return Base64.encodeBase64String(data.getBytes("UTF-8"));
    }
}
