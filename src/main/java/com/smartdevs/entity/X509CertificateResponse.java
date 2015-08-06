package com.smartdevs.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.RDN;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.operator.DefaultAlgorithmNameFinder;

import javax.xml.bind.annotation.XmlRootElement;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;

@XmlRootElement
public class X509CertificateResponse {
    private X500NameMap subject;
    private X500NameMap issuer;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private Date validFrom;
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
    private Date validTo;
    private String version;
    private String serialNumber;
    private String signatureAlgorithm;
    private String publicKey;

    public X509CertificateResponse() {
    }

    public X509CertificateResponse(X509CertificateHolder x509CertificateHolder) {
        subject = new X500NameMap(x509CertificateHolder.getSubject());
        issuer = new X500NameMap(x509CertificateHolder.getIssuer());
        validFrom = x509CertificateHolder.getNotBefore();
        validTo = x509CertificateHolder.getNotAfter();
        version = "V" + x509CertificateHolder.getVersionNumber();
        serialNumber = getByteCode(x509CertificateHolder.getSerialNumber());
        DefaultAlgorithmNameFinder finder = new DefaultAlgorithmNameFinder();
        signatureAlgorithm = finder.getAlgorithmName(x509CertificateHolder.getSignatureAlgorithm());
        publicKey = getByteCode(x509CertificateHolder.getSubjectPublicKeyInfo().getPublicKeyData().getBytes());
    }



    private static class X500NameMap extends LinkedHashMap<String, String>{
        public X500NameMap(X500Name x500Name) {
            put("CN", null);
            put("OU", null);
            put("O", null);
            put("L", null);
            put("ST", null);
            put("C", null);
            try {
                for (Field field : BCStyle.class.getDeclaredFields()) {
                    if (field.getType().equals(ASN1ObjectIdentifier.class)) {
                        ASN1ObjectIdentifier id = (ASN1ObjectIdentifier) field.get(null);
                        put(BCStyle.INSTANCE.oidToDisplayName(id), getX500Field(x500Name, id));
                    }
                }
            } catch (IllegalAccessException ignored) {
            }
            for (Iterator<String> i = keySet().iterator();i.hasNext();) {
                if (get(i.next()) == null) i.remove();
            }
        }

        private String getX500Field(X500Name x500Name, ASN1ObjectIdentifier asn1ObjectIdentifier) {
            RDN[] rdnArray = x500Name.getRDNs(asn1ObjectIdentifier);
            return rdnArray.length == 0 ? null : rdnArray[0].getFirst().getValue().toString();
        }
    }

    private String getByteCode(BigInteger bigInteger) {
        StringBuilder sb = new StringBuilder(bigInteger.toString(16));
        if (sb.length() % 2 == 1) {
            sb.insert(0, '0');
        }
        for (int i = 2;i < sb.length(); i+=3) {
            sb.insert(i, ' ');
        }
        return sb.toString();
    }

    private String getByteCode(byte[] bytes) {
        BigInteger bigInteger = new BigInteger(bytes);
        StringBuilder sb = new StringBuilder(bigInteger.toString(16));
        if (sb.length() % 2 == 1) {
            sb.insert(0, '0');
        }
        for (int i = 2;i < sb.length(); i+=3) {
            sb.insert(i, ' ');
        }
        return sb.toString();
    }

    public X500NameMap getSubject() {
        return subject;
    }

    public void setSubject(X500NameMap subject) {
        this.subject = subject;
    }

    public X500NameMap getIssuer() {
        return issuer;
    }

    public void setIssuer(X500NameMap issuer) {
        this.issuer = issuer;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSignatureAlgorithm() {
        return signatureAlgorithm;
    }

    public void setSignatureAlgorithm(String signatureAlgorithm) {
        this.signatureAlgorithm = signatureAlgorithm;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
