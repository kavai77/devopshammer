package com.smartdevs.engine;

import com.smartdevs.entity.SamlResponse;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;

public class SamlEncoder {
        public SamlResponse encodeSamlRequest( String saml, SamlResponse.BindingFormat bindingFormat ) {
                if ( !isWellFormattedXML( saml ) ) {
                        return SamlResponse.error( "The XML is not well formatted" );
                }
                switch ( bindingFormat ) {
                case POST:
                        return encodePostFormat( saml );
                case REDIRECT:
                        return encodeRedirectFormat( saml );
                default:
                        throw new UnsupportedOperationException(  );
                }
        }

        private SamlResponse encodePostFormat ( String saml ) {
                String encodedSaml = Base64.encodeBase64String( saml.getBytes() );
                return SamlResponse.encoded( encodedSaml, SamlResponse.BindingFormat.POST );
        }

        private SamlResponse encodeRedirectFormat( String saml ) {
                Deflater deflater = new Deflater( Deflater.DEFAULT_COMPRESSION, true );
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                try {
                        deflater.setInput( URLDecoder.decode( saml, "UTF-8" ).getBytes() );
                        deflater.finish();
                        byte[] buffer = new byte[100];
                        while ( !deflater.finished() ) {
                                int length = deflater.deflate( buffer );
                                os.write( buffer, 0, length );
                        }

                        String encodedSaml = Base64.encodeBase64String( os.toByteArray() );
                        String urlEncodedSaml = URLEncoder.encode( encodedSaml, "UTF-8" );
                        return SamlResponse.encoded( urlEncodedSaml, SamlResponse.BindingFormat.REDIRECT );
                } catch ( UnsupportedEncodingException e ) {
                        throw new RuntimeException( e );
                } finally {
                        deflater.end();
                        IOUtils.closeQuietly( os );
                }
        }
    private boolean isWellFormattedXML(String saml) {
                ByteArrayInputStream stream = new ByteArrayInputStream( saml.getBytes( StandardCharsets.UTF_8 ) );
                try {
                        DocumentBuilderFactory.newInstance().newDocumentBuilder().parse( stream );
                        return true;
                } catch ( SAXException e ) {
                        return false;
                } catch ( IOException e ) {
                        return false;
                } catch ( ParserConfigurationException e ) {
                        throw new RuntimeException( e );
                } finally {
                        IOUtils.closeQuietly( stream );
                }
        }
}
