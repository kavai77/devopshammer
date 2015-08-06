package com.smartdevs.engine;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.smartdevs.exception.BadRequestException;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 * Created by coby on 20/10/14.
 */
public class PrettyJsonPrinter {
    public String getPrettyJson(String json) throws BadRequestException {
        try (Reader jsonStringReader = new StringReader(json)) {
            JsonElement jsonElement = jsonParser().parse(new JsonReader(jsonStringReader));
            return gsonBuilder().toJson(jsonElement);
        } catch (JsonSyntaxException | IOException e) {
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
