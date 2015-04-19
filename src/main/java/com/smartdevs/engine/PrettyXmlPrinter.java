package com.smartdevs.engine;

import com.smartdevs.exception.PrettyXmlCreationException;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;

/**
 * Created by coby on 19/10/14.
 */
public class PrettyXmlPrinter {
    public static final String FORMAT_PRETTY_PRINT = "format-pretty-print";
    public static final String XML_DECLARATION_NEED_TO_BE_OUTPUTTED = "xml-declaration";
    public static final String XML_DECLARATION_BEGIN = "<?xml";
    public static final String LS = "LS";

    private String unformattedXml;

    public PrettyXmlPrinter() {
    }

    public PrettyXmlPrinter(String unformattedXml) {
        this.unformattedXml = unformattedXml;
    }

    public String getPrettyXml() throws PrettyXmlCreationException {
        try {
            return domToXmlSerializer().writeToString(createDocumentFromString());
        } catch (Exception e) {
            throw new PrettyXmlCreationException(e);
        }
    }

    private LSSerializer domToXmlSerializer() throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
        final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation(LS);
        final LSSerializer serializedXml = impl.createLSSerializer();

        serializedXml.getDomConfig().setParameter(FORMAT_PRETTY_PRINT, Boolean.TRUE);
        serializedXml.getDomConfig().setParameter(XML_DECLARATION_NEED_TO_BE_OUTPUTTED,
                unformattedXml.startsWith(XML_DECLARATION_BEGIN));

        return serializedXml;
    }

    private Node createDocumentFromString() throws SAXException, IOException, ParserConfigurationException {
        final InputSource src = new InputSource(new StringReader(unformattedXml));
        return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src).getDocumentElement();
    }

    public void setUnformattedXml(String unformattedXml) {
        this.unformattedXml = unformattedXml;
    }
}