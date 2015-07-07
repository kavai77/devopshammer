package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class SamlEncoderTest {
    private SamlEncoder underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new SamlEncoder();
    }

    @Test
    public void testWellFormattedSamlRequest() throws Exception {
        assertEquals(SamlResponse.CodingType.ENCODED, underTest.encodeSamlRequest("<xml/>", SamlResponse.BindingFormat.POST).getCodingType());
        assertEquals(SamlResponse.CodingType.ENCODED, underTest.encodeSamlRequest("<xml></xml>", SamlResponse.BindingFormat.REDIRECT).getCodingType());
        assertEquals(SamlResponse.CodingType.ENCODED, underTest.encodeSamlRequest("   <xml>   </xml>    ", SamlResponse.BindingFormat.POST).getCodingType());
        assertEquals(SamlResponse.CodingType.ENCODED, underTest.encodeSamlRequest("<xml attr=\"\"/>", SamlResponse.BindingFormat.REDIRECT).getCodingType());
    }

    @Test
     public void testPostEncodingSamlRequest() throws Exception {
        SamlResponse samlResponse = underTest.encodeSamlRequest(SamlExamples.SAMPLE1.decoded, SamlResponse.BindingFormat.POST );
        assertEquals(SamlResponse.CodingType.ENCODED, samlResponse.getCodingType());
        assertEquals(SamlResponse.BindingFormat.POST, samlResponse.getBindingFormat());
        assertEquals( SamlExamples.SAMPLE1.postEncoded, samlResponse.getResult() );
    }

    @Test
    public void testRedirectEncodingSamlRequest() throws Exception {
        SamlResponse samlResponse = underTest.encodeSamlRequest(SamlExamples.SAMPLE1.decoded, SamlResponse.BindingFormat.REDIRECT );
        assertEquals(SamlResponse.CodingType.ENCODED, samlResponse.getCodingType());
        assertEquals(SamlResponse.BindingFormat.REDIRECT, samlResponse.getBindingFormat());
        assertEquals( SamlExamples.SAMPLE1.redirectEncoded, samlResponse.getResult() );
    }
}
