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
                String encoded = "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";
                SamlResponse samlResponse = samlDecoder.decodeSamlRequest( encoded );
                assertEquals( SamlResponse.CodingType.DECODED, samlResponse.getCodingType() );
                assertEquals( SamlResponse.BindingFormat.REDIRECT, samlResponse.getBindingFormat() );
                assertEquals( IOUtils.toString( getClass().getResourceAsStream( "/decodedRedirectSaml" ) ).trim(), samlResponse.getResult().trim() );
        }
}
