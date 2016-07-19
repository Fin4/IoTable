package org.iotable.core.model.ioUnits.typeadapters;


import com.google.gson.*;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.IoUnit;

import java.lang.reflect.Type;

public class AnalogOutputTypeAdapter implements JsonDeserializer<AnalogOutput> {
    @Override
    public AnalogOutput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        int number = jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt();
        String address = jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString();
        String desc = jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString();
        String symbol = jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString();

        return new AnalogOutput(new IoUnit(symbol, desc, address, number));
    }
}
