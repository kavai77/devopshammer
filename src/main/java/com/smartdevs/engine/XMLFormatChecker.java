package com.smartdevs.engine;

import com.smartdevs.exception.BadRequestException;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by 212418603 on 2015.07.07..
 */
public class XMLFormatChecker {
    public static void checkXMLFormat(String saml) {
        try (ByteArrayInputStream stream = new ByteArrayInputStream(saml.getBytes(StandardCharsets.UTF_8))) {
            DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
        } catch (SAXException | IOException e) {
            throw new BadRequestException("The XML is not well formatted: " + e.getMessage());
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }
}
