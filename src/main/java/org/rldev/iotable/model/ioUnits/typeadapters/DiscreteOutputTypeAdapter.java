package org.rldev.iotable.model.ioUnits.typeadapters;

import com.google.gson.*;
import org.rldev.iotable.model.ioUnits.DiscreteOutput;

import java.lang.reflect.Type;



public class DiscreteOutputTypeAdapter implements JsonDeserializer<DiscreteOutput> {
    @Override
    public DiscreteOutput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        DiscreteOutput discreteOutput = new DiscreteOutput();
        JsonObject jsonObject = json.getAsJsonObject();

        discreteOutput.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        discreteOutput.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString());
        discreteOutput.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString());
        discreteOutput.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString());

        return discreteOutput;
    }
}
