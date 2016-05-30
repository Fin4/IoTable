package org.rldev.iotable.model.ioUnits.typeadapters;

import com.google.gson.*;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DigitalInput;

import java.lang.reflect.Type;

public class DigitalInputTypeAdapter implements JsonDeserializer<DigitalInput> {
    @Override
    public DigitalInput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        DigitalInput di = new DigitalInput();
        JsonObject jsonObject = json.getAsJsonObject();

        di.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        di.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString());
        di.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString());
        di.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString());

        return di;
    }
}
