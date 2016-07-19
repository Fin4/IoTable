package org.iotable.core.model.ioUnits.typeadapters;

import com.google.gson.*;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.IoUnit;

import java.lang.reflect.Type;

public class DiscreteInputTypeAdapter implements JsonDeserializer<DiscreteInput> {
    @Override
    public DiscreteInput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        int number = jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt();
        String address = jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString();
        String desc = jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString();
        String symbol = jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString();

        return new DiscreteInput(new IoUnit(symbol, desc, address, number));
    }
}
