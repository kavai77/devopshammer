package com.smartdevs.entity;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SamlResponse {
    public enum CodingType {
        ENCODED, DECODED, ERROR
    }

    public enum BindingFormat {
        REDIRECT, POST
    }

    private String result;
    private CodingType codingType;
    private BindingFormat bindingFormat;
    private String errorMessage;

    public SamlResponse() {
    }

    public static SamlResponse encoded(String result) {
        SamlResponse samlResponse = new SamlResponse();
        samlResponse.setCodingType(CodingType.ENCODED);
        samlResponse.setResult(result);
        return samlResponse;
    }

    public static SamlResponse decoded(String result, BindingFormat bindingFormat) {
        SamlResponse samlResponse = new SamlResponse();
        samlResponse.setCodingType(CodingType.DECODED);
        samlResponse.setBindingFormat(bindingFormat);
        samlResponse.setResult(result);
        return samlResponse;
    }

    public static SamlResponse error(String errorMessage) {
        SamlResponse samlResponse = new SamlResponse();
        samlResponse.setCodingType(CodingType.ERROR);
        samlResponse.setErrorMessage(errorMessage);
        return samlResponse;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public CodingType getCodingType() {
        return codingType;
    }

    public void setCodingType(CodingType codingType) {
        this.codingType = codingType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public BindingFormat getBindingFormat() {
        return bindingFormat;
    }

    public void setBindingFormat(BindingFormat bindingFormat) {
        this.bindingFormat = bindingFormat;
    }
}
