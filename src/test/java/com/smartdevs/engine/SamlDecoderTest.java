package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SamlDecoderTest {
        private SamlDecoder samlDecoder;

        @Before
        public void setUp() throws Exception {
                samlDecoder = new SamlDecoder();
        }

        @Test
        public void testRedirectDecode() throws Exception {
                SamlResponse samlResponse = samlDecoder.decodeSamlRequest( SamlExamples.SAMPLE1.redirectEncoded, SamlResponse.BindingFormat.REDIRECT );
                assertEquals( SamlResponse.CodingType.DECODED, samlResponse.getCodingType() );
                assertEquals( SamlResponse.BindingFormat.REDIRECT, samlResponse.getBindingFormat() );
                assertEquals( SamlExamples.SAMPLE1.decoded, samlResponse.getResult() );
        }

        @Test
        public void testPostDecode() throws Exception {
                SamlResponse samlResponse = samlDecoder.decodeSamlRequest( SamlExamples.SAMPLE1.postEncoded, SamlResponse.BindingFormat.POST );
                assertEquals( SamlResponse.CodingType.DECODED, samlResponse.getCodingType() );
                assertEquals( SamlResponse.BindingFormat.POST, samlResponse.getBindingFormat() );
                assertEquals( SamlExamples.SAMPLE1.decoded, samlResponse.getResult() );
        }
}
