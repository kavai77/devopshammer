package com.smartdevs.engine;

import com.google.inject.Inject;
import com.smartdevs.entity.SamlResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;

public class SamlEngine {
        private static final SamlResponse EMPTY_RESPONSE = new SamlResponse();

        @Inject SamlEncoder samlEncoder;
        @Inject SamlDecoder samlDecoder;

        public SamlResponse decodeOrEncode( String saml ) {
                if ( StringUtils.isBlank( saml ) ) {
                        return EMPTY_RESPONSE;
                }
                if ( Base64.isBase64( saml ) ) {
                        return samlDecoder.decodeSamlRequest( saml );
                } else {
                        return samlEncoder.encodeSamlRequest( saml );
                }
        }
}
