package com.smartdevs.service;

import com.smartdevs.annotation.EmptyInputValidator;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.engine.PrettyXmlPrinter;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.UnsupportedEncodingException;

/**
 * Created by coby on 14/04/15.
 */
@Path("xml")
public class XmlService {
    @Inject
    PrettyXmlPrinter prettyXmlPrinter;

    @POST
    @Path("pretty")
    @Produces(MediaType.APPLICATION_XML)
    @MaxInputLengthValidator
    @EmptyInputValidator
    public String decode(String xml) throws UnsupportedEncodingException {
        return prettyXmlPrinter.getPrettyXml(xml);
    }
}
