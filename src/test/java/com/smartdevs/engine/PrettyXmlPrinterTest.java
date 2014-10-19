package com.smartdevs.engine;

import org.junit.Test;

/**
 * Created by coby on 19/10/14.
 */
public class PrettyXmlPrinterTest {
    @Test(expected = RuntimeException.class)
    public void failsWhenXmlInputIsNull() throws Exception {
        PrettyXmlPrinter printer = new PrettyXmlPrinter(null);
        printer.getPrettyXml();
    }

    @Test(expected = RuntimeException.class)
    public void failsWhenXmRootNodeIsMissingTheClosingTag() throws Exception {
        PrettyXmlPrinter printer = new PrettyXmlPrinter("<rootWithNoCloseTag>");
        printer.getPrettyXml();
    }

    @Test
    public void worksWhenXmRootNodeIsDefined() throws Exception {
        PrettyXmlPrinter printer = new PrettyXmlPrinter("<wrongsyntax/>");
        printer.getPrettyXml();
    }

    @Test(expected = RuntimeException.class)
    public void failsWhenXmAttributeIsWronglyDefined() throws Exception {
        PrettyXmlPrinter printer = new PrettyXmlPrinter("<wrongsyntax attr=\"/>");
        printer.getPrettyXml();
    }

    @Test
    public void worksWhenXmAttributeIsProperlyDefined() throws Exception {
        PrettyXmlPrinter printer = new PrettyXmlPrinter("<wrongsyntax attr=\"\" />");
        printer.getPrettyXml();
    }
}