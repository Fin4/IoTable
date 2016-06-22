package org.iotable.core.model.ioUnits.typeadapters;


import com.google.gson.*;
import org.iotable.core.model.ioUnits.AnalogOutput;

import java.lang.reflect.Type;

public class AnalogOutputTypeAdapter implements JsonDeserializer<AnalogOutput> {
    @Override
    public AnalogOutput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        AnalogOutput ao = new AnalogOutput();
        JsonObject jsonObject = json.getAsJsonObject();

        ao.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        ao.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString());
        ao.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString());
        ao.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString());

        return ao;
    }
}
