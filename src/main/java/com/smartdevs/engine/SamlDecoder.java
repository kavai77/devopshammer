package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import com.smartdevs.exception.BadRequestException;
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
                if (length == 0) break;
                os.write(buffer, 0, length);
            }

            String result = new String(os.toByteArray());
            XMLFormatChecker.checkXMLFormat(result);
            return SamlResponse.decoded(result, SamlResponse.BindingFormat.REDIRECT);
        } catch (DataFormatException | UnsupportedEncodingException e) {
            throw new BadRequestException( e );
        } finally {
            inflater.end();
        }
    }

    public SamlResponse decodePostFormat(String saml) {
        try {
            byte[] decode = Base64.decodeBase64(saml);
            String result = new String(decode);
            XMLFormatChecker.checkXMLFormat(result);
            return SamlResponse.decoded(result, SamlResponse.BindingFormat.POST);
        } catch (RuntimeException e) {
            throw new BadRequestException( e );
        }
    }

}
