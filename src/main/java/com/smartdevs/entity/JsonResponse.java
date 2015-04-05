package com.smartdevs.entity;

/**
 * Created by coby on 05/04/15.
 */
public class JsonResponse {
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

    @Override
    public String toString() {
        if (!valid) {
            json = EMPTY_JSON;
        }

        return "{ \"valid\":" + "\"" + valid + "\"" +
                ", \"json\":" + json + "}";
    }
}
