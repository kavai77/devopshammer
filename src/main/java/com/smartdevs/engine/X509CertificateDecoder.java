package com.smartdevs.engine;

import com.smartdevs.exception.BadRequestException;
import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class X509CertificateDecoder {
    public X509Certificate decodeCertificate(String rawCert) throws BadRequestException {
        String formattedRawCert = rawCert
                .replaceAll("-*BEGIN CERTIFICATE-*", "")
                .replaceAll("-*END CERTIFICATE-*", "")
                .replaceAll("\\s", "");
        try (InputStream in = new ByteArrayInputStream(Base64.decodeBase64(formattedRawCert))) {
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            return  (X509Certificate) certFactory.generateCertificate(in);
        } catch (CertificateException | IOException e) {
            throw new BadRequestException(e);
        }
    }
}
