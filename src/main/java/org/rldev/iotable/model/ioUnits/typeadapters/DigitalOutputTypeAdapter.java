package org.rldev.iotable.model.ioUnits.typeadapters;

import com.google.gson.*;
import org.rldev.iotable.model.ioUnits.DigitalInput;
import org.rldev.iotable.model.ioUnits.DigitalOutput;

import java.lang.reflect.Type;



public class DigitalOutputTypeAdapter implements JsonDeserializer<DigitalOutput> {
    @Override
    public DigitalOutput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        DigitalOutput digitalOutput = new DigitalOutput();
        JsonObject jsonObject = json.getAsJsonObject();

        digitalOutput.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        digitalOutput.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString());
        digitalOutput.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString());
        digitalOutput.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString());

        return digitalOutput;
    }
}
