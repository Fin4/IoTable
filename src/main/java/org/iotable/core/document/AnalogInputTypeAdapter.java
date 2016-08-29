package org.iotable.core.document;

import com.google.gson.*;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.IoUnit;

import java.lang.reflect.Type;

final class AnalogInputTypeAdapter implements JsonDeserializer<AnalogInput> {
    @Override
    public AnalogInput deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        JsonObject jsonObject = json.getAsJsonObject();

        int number = jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt();
        String address = jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString();
        String desc = jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString();
        String symbol = jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString();
        String eu = jsonObject.get("engUnits").isJsonNull() ? "" : jsonObject.get("engUnits").getAsString();

        return new AnalogInput(new IoUnit(symbol, desc, address, number), eu);
    }
}
