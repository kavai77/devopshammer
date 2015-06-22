package com.smartdevs.service;

import com.google.inject.Inject;
import com.smartdevs.annotation.MaxInputLengthValidator;
import com.smartdevs.engine.PrettyXmlPrinter;
import com.smartdevs.entity.XmlResponse;

import javax.ws.rs.Consumes;
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
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @MaxInputLengthValidator
    public String decode(String xml) throws UnsupportedEncodingException {
        prettyXmlPrinter.setUnformattedXml(xml);
        try {
            return new XmlResponse(true, prettyXmlPrinter.getPrettyXml()).toString();
        } catch (Exception e) {
            XmlResponse xmlResponse = new XmlResponse(false, null);
            xmlResponse.setMessage(e.getCause().getMessage());
            return xmlResponse.toString();
        }
    }
}
