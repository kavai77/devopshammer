package com.smartdevs.engine;

import org.apache.commons.io.IOUtils;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;

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
            X509CertificateHolder x509Certificate = decoder.convertPemToX509CertificateHolder(cer);
            assertEquals("accounts.accesscontrol.windows.net", x509Certificate.getIssuer().getRDNs(BCStyle.CN)[0].getFirst().getValue().toString());
        }
    }
}