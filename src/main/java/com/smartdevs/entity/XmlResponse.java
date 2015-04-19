package com.smartdevs.entity;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * Created by coby on 14/04/15.
 */
public class XmlResponse {
    public static final String EMPTY_XML = "";
    private boolean valid;
    private String xml;
    private String message;

    public XmlResponse(boolean valid, String xml) {
        this.valid = valid;
        this.xml = xml;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String getBase64EncodedAndHtmlReadyString(String input) {
        if (StringUtils.isNotEmpty(input)) {
            return new String(encodeBase64(input.replaceAll(System.getProperty("line.separator"), "\r\n").getBytes()));
        }
        return "";
    }

    @Override
    public String toString() {
        if (!valid) {
            xml = EMPTY_XML;
        }
        return "{ \"valid\":" + "\"" + valid + "\"" +
                ", \"xml\":" + "\"" + getBase64EncodedAndHtmlReadyString(xml) + "\"" +
                ", \"message\":" + "\"" + getBase64EncodedAndHtmlReadyString(message) + "\"}";
    }

}
