package com.smartdevs.entity;

import org.apache.commons.lang.StringUtils;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

/**
 * Created by coby on 05/04/15.
 */
public class JsonResponse extends Response {
    public static final String EMPTY_JSON = "\"\"";
    private boolean valid;
    private String json;

    public JsonResponse(boolean valid, String json) {
        this.valid = valid;
        this.json = json;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
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
            json = EMPTY_JSON;
        }

        return "{ \"valid\":" + "\"" + valid + "\"" +
                ", \"json\":" + "\"" + getBase64EncodedAndHtmlReadyString(json) + "\"" +
                ", \"message\":" + "\"" + getBase64EncodedAndHtmlReadyString(message) + "\"}";
    }
}
