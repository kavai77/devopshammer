package com.smartdevs.engine;

import org.apache.commons.codec.binary.Base64;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class X509CertificateDecoder {
    public X509Certificate decodeCertificate(String rawCert) throws CertificateException {
        String formattedRawCert = rawCert
                .replaceAll("-*BEGIN CERTIFICATE-*", "")
                .replaceAll("-*END CERTIFICATE-*", "")
                .replaceAll("\\s", "");
        CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
        try (InputStream in = new ByteArrayInputStream(Base64.decodeBase64(formattedRawCert))) {
            return  (X509Certificate) certFactory.generateCertificate(in);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
