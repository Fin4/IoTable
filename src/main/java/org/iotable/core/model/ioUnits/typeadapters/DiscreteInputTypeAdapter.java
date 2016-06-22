package org.iotable.core.model.ioUnits.typeadapters;

import com.google.gson.*;
import org.iotable.core.model.ioUnits.DiscreteInput;

import java.lang.reflect.Type;

public class DiscreteInputTypeAdapter implements JsonDeserializer<DiscreteInput> {
    @Override
    public DiscreteInput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        DiscreteInput di = new DiscreteInput();
        JsonObject jsonObject = json.getAsJsonObject();

        di.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        di.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString());
        di.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString());
        di.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString());

        return di;
    }
}
