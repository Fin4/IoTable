package org.iotable.core.model.ioUnits.typeadapters;

import com.google.gson.*;
import org.iotable.core.model.ioUnits.AnalogInput;

import java.lang.reflect.Type;

public class AnalogInputTypeAdapter implements JsonDeserializer<AnalogInput> {
    @Override
    public AnalogInput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        AnalogInput ai = new AnalogInput();
        JsonObject jsonObject = json.getAsJsonObject();

        ai.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        ai.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString().trim());
        ai.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString().trim());
        ai.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString().trim());
        ai.setEngUnits(jsonObject.get("engUnits").isJsonNull() ? "" : jsonObject.get("engUnits").getAsString().trim());

        return ai;
    }
}
