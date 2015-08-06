package com.smartdevs.engine;

import com.smartdevs.exception.BadRequestException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.openssl.PEMParser;

import java.io.IOException;
import java.io.StringReader;
import java.security.Security;

public class X509CertificateDecoder {
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    public X509CertificateHolder convertPemToX509CertificateHolder(String rawCert) throws BadRequestException {
        try {
            PEMParser pemParser = new PEMParser(new StringReader(rawCert));
            Object result = pemParser.readObject();
            if (!(result instanceof X509CertificateHolder)) {
                throw new BadRequestException("Not a valid certificate file.");
            }
            return (X509CertificateHolder) result;
        } catch (IOException e) {
            throw new BadRequestException(e);
        }
    }
}
