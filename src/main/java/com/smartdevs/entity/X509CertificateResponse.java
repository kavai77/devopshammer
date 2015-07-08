package com.smartdevs.entity;

import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigInteger;
import java.security.cert.CertificateParsingException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@XmlRootElement
public class X509CertificateResponse {
    private String subjectDN;
    private String issuerDN;
    private Date notBefore;
    private Date notAfter;
    private ArrayList<String> extendedKeyUsage;
    private String sigAlgName;
    private String sigAlgOID;
    private String x500Principal;
    private ArrayList<ArrayList<?>> issuerAlternativeNames;
    private BigInteger serialNumber;
    private int version;
    private int basicConstraints;
    private boolean[] issuerUniqueID;
    private boolean[] keyUsage;
    private byte[] sigAlgParams;
    private byte[] signature;
    private boolean[] subjectUniqueID;

    public X509CertificateResponse() {
    }

    public X509CertificateResponse(X509Certificate x509Certificate) {
        subjectDN = x509Certificate.getSubjectDN().toString();
        issuerDN = x509Certificate.getIssuerDN().toString();
        notBefore = x509Certificate.getNotBefore();
        notAfter = x509Certificate.getNotAfter();
        try {
            extendedKeyUsage = new ArrayList<>(x509Certificate.getExtendedKeyUsage());
        } catch (CertificateParsingException | NullPointerException ignored) {
        }
        sigAlgName = x509Certificate.getSigAlgName();
        sigAlgOID = x509Certificate.getSigAlgOID();
        x500Principal = x509Certificate.getSubjectX500Principal().toString();
        try {
            issuerAlternativeNames = new ArrayList<>();
            for(List<?> alternativeNames: x509Certificate.getIssuerAlternativeNames()) {
                issuerAlternativeNames.add(new ArrayList<Object>(alternativeNames));
            }
        } catch (CertificateParsingException | NullPointerException ignored) {
        }
        serialNumber = x509Certificate.getSerialNumber();
        version = x509Certificate.getVersion();
        basicConstraints = x509Certificate.getBasicConstraints();
        issuerUniqueID = x509Certificate.getIssuerUniqueID();
        keyUsage = x509Certificate.getKeyUsage();
        sigAlgParams = x509Certificate.getSigAlgParams();
        signature = x509Certificate.getSignature();
        subjectUniqueID = x509Certificate.getSubjectUniqueID();
    }

    public boolean[] getSubjectUniqueID() {
        return subjectUniqueID;
    }

    public void setSubjectUniqueID(boolean[] subjectUniqueID) {
        this.subjectUniqueID = subjectUniqueID;
    }

    public byte[] getSigAlgParams() {
        return sigAlgParams;
    }

    public void setSigAlgParams(byte[] sigAlgParams) {
        this.sigAlgParams = sigAlgParams;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public boolean[] getKeyUsage() {
        return keyUsage;
    }

    public void setKeyUsage(boolean[] keyUsage) {
        this.keyUsage = keyUsage;
    }

    public boolean[] getIssuerUniqueID() {
        return issuerUniqueID;
    }

    public void setIssuerUniqueID(boolean[] issuerUniqueID) {
        this.issuerUniqueID = issuerUniqueID;
    }

    public int getBasicConstraints() {
        return basicConstraints;
    }

    public void setBasicConstraints(int basicConstraints) {
        this.basicConstraints = basicConstraints;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSubjectDN() {
        return subjectDN;
    }

    public String getIssuerDN() {
        return issuerDN;
    }

    public Date getNotBefore() {
        return notBefore;
    }

    public Date getNotAfter() {
        return notAfter;
    }

    public ArrayList<String> getExtendedKeyUsage() {
        return extendedKeyUsage;
    }

    public void setExtendedKeyUsage(ArrayList<String> extendedKeyUsage) {
        this.extendedKeyUsage = extendedKeyUsage;
    }

    public String getSigAlgName() {
        return sigAlgName;
    }

    public void setSigAlgName(String sigAlgName) {
        this.sigAlgName = sigAlgName;
    }

    public String getSigAlgOID() {
        return sigAlgOID;
    }

    public void setSigAlgOID(String sigAlgOID) {
        this.sigAlgOID = sigAlgOID;
    }

    public String getX500Principal() {
        return x500Principal;
    }

    public void setX500Principal(String x500Principal) {
        this.x500Principal = x500Principal;
    }

    public ArrayList<ArrayList<?>> getIssuerAlternativeNames() {
        return issuerAlternativeNames;
    }

    public void setIssuerAlternativeNames(ArrayList<ArrayList<?>> issuerAlternativeNames) {
        this.issuerAlternativeNames = issuerAlternativeNames;
    }

    public BigInteger getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(BigInteger serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setSubjectDN(String subjectDN) {
        this.subjectDN = subjectDN;
    }

    public void setIssuerDN(String issuerDN) {
        this.issuerDN = issuerDN;
    }

    public void setNotBefore(Date notBefore) {
        this.notBefore = notBefore;
    }

    public void setNotAfter(Date notAfter) {
        this.notAfter = notAfter;
    }
}
