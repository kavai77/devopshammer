package com.smartdevs.service;

import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.exception.InputLengthException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.CharEncoding;

import javax.ws.rs.Consumes;
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
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @MaxInputLengthValidator
    public String decode(String data) throws UnsupportedEncodingException, InputLengthException {
        return new String(Base64.decodeBase64(data), CharEncoding.UTF_8);
    }

    @POST
    @Path("encode")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    @MaxInputLengthValidator
    public String encode(String data) throws UnsupportedEncodingException, InputLengthException {
        return Base64.encodeBase64String(data.getBytes(CharEncoding.UTF_8));
    }
}
