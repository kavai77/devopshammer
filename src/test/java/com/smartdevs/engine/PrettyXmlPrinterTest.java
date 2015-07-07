package com.smartdevs.engine;

import com.smartdevs.exception.BadRequestException;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by coby on 19/10/14.
 */
public class PrettyXmlPrinterTest {

    private PrettyXmlPrinter underTest;
    
    @Before
    public void setUp() throws Exception {
        underTest = new PrettyXmlPrinter();
    }

    @Test(expected = BadRequestException.class)
    public void failsWhenXmlInputIsNull() throws Exception {
        underTest.getPrettyXml(null);
    }

    @Test(expected = BadRequestException.class)
    public void failsWhenXmRootNodeIsMissingTheClosingTag() throws Exception {
        underTest.getPrettyXml("<rootWithNoCloseTag>");
    }

    @Test
    public void worksWhenXmRootNodeIsDefined() throws Exception {
        underTest.getPrettyXml("<wrongsyntax/>");
    }

    @Test(expected = BadRequestException.class)
    public void failsWhenXmAttributeIsWronglyDefined() throws Exception {
        underTest.getPrettyXml("<wrongsyntax attr=\"/>");
    }

    @Test
    public void worksWhenXmAttributeIsProperlyDefined() throws Exception {
        underTest.getPrettyXml("<wrongsyntax attr=\"\" />");
    }
}