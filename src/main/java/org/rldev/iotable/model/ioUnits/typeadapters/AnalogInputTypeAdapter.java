package org.rldev.iotable.model.ioUnits.typeadapters;

import com.google.gson.*;
import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.IoUnit;

import java.lang.reflect.Type;

public class AnalogInputTypeAdapter implements JsonDeserializer<AnalogInput> {
    @Override
    public AnalogInput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        AnalogInput ai = new AnalogInput();
        JsonObject jsonObject = json.getAsJsonObject();

        ai.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        ai.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString());
        ai.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString());
        ai.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString());
        ai.setEngUnits(jsonObject.get("engUnits").isJsonNull() ? "" : jsonObject.get("engUnits").getAsString());

        return ai;
    }
}