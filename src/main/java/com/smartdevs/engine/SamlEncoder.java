package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class SamlEncoder {
    public SamlResponse encodeSamlRequest(String saml) {
        if (!isWellFormattedXML(saml)) {
            return SamlResponse.error("The XML is not well formatted");
        }
        //                try {
        String encodedSaml = Base64.encodeBase64String(saml.getBytes());
        //                        String urlEncoded = URLEncoder.encode( encodedSaml, "UTF-8" );
        return SamlResponse.encoded(encodedSaml);
        //                } catch ( UnsupportedEncodingException e ) {
        //                        throw new RuntimeException( e );
        //                }
    }

    private boolean isWellFormattedXML(String saml) {
        ByteArrayInputStream stream = new ByteArrayInputStream(saml.getBytes(StandardCharsets.UTF_8));
        try {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            return true;
        } catch (SAXException e) {
            return false;
        } catch (IOException e) {
            return false;
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(stream);
        }
    }
}
