package com.smartdevs.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.smartdevs.exception.PrettyJsonCreationException;

/**
 * Created by coby on 20/10/14.
 */
public class PrettyJsonPrinter {
    private String json;

    public PrettyJsonPrinter(String jsonString) {
        this.json = jsonString;
    }

    public String getPrettyJson() throws PrettyJsonCreationException {
        try {
        JsonElement jsonElement = jsonParser().parse(json);
        return gsonBuilder().toJson(jsonElement);
        }
        catch (Exception ex) {
            throw new PrettyJsonCreationException(ex);
        }
    }

    private Gson gsonBuilder() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    private JsonParser jsonParser() {
        return new JsonParser();
    }


}
