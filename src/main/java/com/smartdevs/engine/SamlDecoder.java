package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

public class SamlDecoder {

    public SamlResponse decodeSamlRequest( String saml, SamlResponse.BindingFormat bindingFormat ) {
        switch ( bindingFormat ) {
        case POST:
            return decodePostFormat( saml );
        case REDIRECT:
            return decodeRedirectFormat( saml );
        default:
            throw new UnsupportedOperationException(  );
        }
    }

    public SamlResponse decodeRedirectFormat(String saml) {
        Inflater inflater = new Inflater(true);
        try {
            String urlDecodedSaml = URLDecoder.decode( saml, "UTF-8" );
            byte[] decode = Base64.decodeBase64(urlDecodedSaml);

            inflater.setInput(decode);
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            byte[] buffer = new byte[100];
            while (!inflater.finished()) {
                int length = inflater.inflate(buffer);
                os.write(buffer, 0, length);
            }

            return SamlResponse.decoded(new String(os.toByteArray()), SamlResponse.BindingFormat.REDIRECT);
        } catch (DataFormatException | UnsupportedEncodingException e) {
            throw new IllegalArgumentException( e );
        } finally {
            inflater.end();
        }
    }

    public SamlResponse decodePostFormat(String saml) {
        byte[] decode = Base64.decodeBase64(saml);
        return SamlResponse.decoded(new String(decode), SamlResponse.BindingFormat.POST);
    }
}
