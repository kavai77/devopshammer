package com.smartdevs.engine;

import com.google.gson.*;
import com.smartdevs.exception.BadRequestException;

/**
 * Created by coby on 20/10/14.
 */
public class PrettyJsonPrinter {
    public String getPrettyJson(String json) throws BadRequestException {
        try {
            JsonElement jsonElement = jsonParser().parse(json);
            return gsonBuilder().toJson(jsonElement);
        } catch (JsonSyntaxException e) {
            throw new BadRequestException(e.getCause().getMessage());
        }
    }

    private Gson gsonBuilder() {
        return new GsonBuilder().setPrettyPrinting().create();
    }

    private JsonParser jsonParser() {
        return new JsonParser();
    }

}
