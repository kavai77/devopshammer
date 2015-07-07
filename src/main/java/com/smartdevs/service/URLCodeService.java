package com.smartdevs.service;

import com.smartdevs.annotation.EmptyInputValidator;
import com.smartdevs.annotation.MaxInputLengthValidator;
import org.apache.commons.lang.CharEncoding;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by Kávai on 2014.10.19..
 */
@Path("url")
public class URLCodeService {
    @POST
    @Path("decode")
    @Produces(MediaType.TEXT_PLAIN)
    @MaxInputLengthValidator
    @EmptyInputValidator
    public String decode(String data) throws UnsupportedEncodingException {
        return URLDecoder.decode(data, CharEncoding.UTF_8);
    }

    @POST
    @Path("encode")
    @Produces(MediaType.TEXT_PLAIN)
    @MaxInputLengthValidator
    @EmptyInputValidator
    public String encode(String data) throws UnsupportedEncodingException {
        return URLEncoder.encode(data, CharEncoding.UTF_8);
    }
}
