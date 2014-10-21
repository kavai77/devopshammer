package com.smartdevs.engine;

import com.smartdevs.engine.SamlEncoder;
import com.smartdevs.entity.SamlResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SamlEncoderTest {
    private SamlEncoder samlEncoder;

    @Before
    public void setUp() throws Exception {
        samlEncoder = new SamlEncoder();
    }

    @Test
    public void testWrongFormattedSamlRequest() throws Exception {
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("", null ).getCodingType());
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("    ", null).getCodingType());
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("text", null).getCodingType());
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("<xml", null).getCodingType());
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("xml>", null).getCodingType());
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("<xml>", null).getCodingType());
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("<xml attr=\"/>", null).getCodingType());
        assertEquals(SamlResponse.CodingType.ERROR, samlEncoder.encodeSamlRequest("<xml></xml1>", null).getCodingType());
    }

    @Test
    public void testWellFormattedSamlRequest() throws Exception {
        assertEquals(SamlResponse.CodingType.ENCODED, samlEncoder.encodeSamlRequest("<xml/>", SamlResponse.BindingFormat.POST ).getCodingType());
        assertEquals(SamlResponse.CodingType.ENCODED, samlEncoder.encodeSamlRequest("<xml></xml>", SamlResponse.BindingFormat.REDIRECT ).getCodingType());
        assertEquals(SamlResponse.CodingType.ENCODED, samlEncoder.encodeSamlRequest("   <xml>   </xml>    ", SamlResponse.BindingFormat.POST ).getCodingType());
        assertEquals(SamlResponse.CodingType.ENCODED, samlEncoder.encodeSamlRequest("<xml attr=\"\"/>", SamlResponse.BindingFormat.REDIRECT ).getCodingType());
    }

    @Test
    public void testEncodingSamlRequest() throws Exception {
        String samlXML = "<root><bla/><bla2>text</bla2></root>";
        SamlResponse samlResponse = samlEncoder.encodeSamlRequest(samlXML, SamlResponse.BindingFormat.POST );
        assertEquals(SamlResponse.CodingType.ENCODED, samlResponse.getCodingType());
        assertNull(samlResponse.getErrorMessage());
        assertEquals("PHJvb3Q+PGJsYS8+PGJsYTI+dGV4dDwvYmxhMj48L3Jvb3Q+", samlResponse.getResult());
    }
}
