package org.iotable.core.document;

import com.google.gson.*;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.model.ioUnits.IoUnit;

import java.lang.reflect.Type;


final class DiscreteOutputTypeAdapter implements JsonDeserializer<DiscreteOutput> {
    @Override
    public DiscreteOutput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        int number = jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt();
        String address = jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString();
        String desc = jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString();
        String symbol = jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString();

        return new DiscreteOutput(new IoUnit(symbol, desc, address, number));
    }
}
