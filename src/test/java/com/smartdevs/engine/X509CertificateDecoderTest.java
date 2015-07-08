package com.smartdevs.engine;

import org.apache.commons.io.IOUtils;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.security.cert.X509Certificate;

import static org.junit.Assert.assertEquals;

public class X509CertificateDecoderTest {

    private X509CertificateDecoder decoder;

    @Before
    public void setUp() throws Exception {
        decoder = new X509CertificateDecoder();
    }

    @Test
    public void testDecodeCertificate() throws Exception {
        try (InputStream cerUtils = X509CertificateDecoderTest.class.getResourceAsStream("/certTest.cer")) {
            String cer = IOUtils.toString(cerUtils);
            X509Certificate x509Certificate = decoder.decodeCertificate(cer);
            assertEquals("CN=accounts.accesscontrol.windows.net", x509Certificate.getSubjectDN().getName());
        }
    }
}