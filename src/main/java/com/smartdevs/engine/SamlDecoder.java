package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class SamlDecoder {
        public SamlResponse decodeSamlRequest( String saml ) {
                byte[] decode = Base64.decodeBase64( saml );
                Inflater inflater = new Inflater( true );
                inflater.setInput( decode );
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                try {
                        byte[] buffer = new byte[100];
                        while ( !inflater.finished() ) {
                                int length = inflater.inflate( buffer );
                                os.write( buffer, 0, length );
                        }

                        return SamlResponse.decoded( new String( os.toByteArray() ), SamlResponse.BindingFormat.REDIRECT );
                } catch ( DataFormatException e ) {
                        return SamlResponse.decoded( new String( decode ), SamlResponse.BindingFormat.POST );
                } finally {
                        inflater.end();
                }
        }
}
