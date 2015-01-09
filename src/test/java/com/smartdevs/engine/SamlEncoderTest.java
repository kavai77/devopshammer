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
     public void testPostEncodingSamlRequest() throws Exception {
        SamlResponse samlResponse = samlEncoder.encodeSamlRequest(SamlExamples.SAMPLE1.decoded, SamlResponse.BindingFormat.POST );
        assertEquals(SamlResponse.CodingType.ENCODED, samlResponse.getCodingType());
        assertNull( samlResponse.getErrorMessage() );
        assertEquals( SamlExamples.SAMPLE1.postEncoded, samlResponse.getResult() );
    }

    @Test
    public void testRedirectEncodingSamlRequest() throws Exception {
        SamlResponse samlResponse = samlEncoder.encodeSamlRequest(SamlExamples.SAMPLE1.decoded, SamlResponse.BindingFormat.REDIRECT );
        assertEquals(SamlResponse.CodingType.ENCODED, samlResponse.getCodingType());
        assertNull( samlResponse.getErrorMessage() );
        assertEquals( SamlExamples.SAMPLE1.redirectEncoded, samlResponse.getResult() );
    }
}
