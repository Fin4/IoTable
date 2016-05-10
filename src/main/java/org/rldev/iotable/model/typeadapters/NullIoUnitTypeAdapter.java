package org.rldev.iotable.model.typeadapters;


import com.google.gson.*;
import org.rldev.iotable.model.IoUnit;

import java.lang.reflect.Type;
import java.util.Optional;

public class NullIoUnitTypeAdapter implements JsonDeserializer<IoUnit> {

    @Override
    public IoUnit deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        IoUnit ioUnit = new IoUnit();
        JsonObject jsonObject = json.getAsJsonObject();

        ioUnit.setNumber(jsonObject.get("number").isJsonNull() ? 0 : jsonObject.get("number").getAsInt());
        ioUnit.setAddress(jsonObject.get("address").isJsonNull() ? "" : jsonObject.get("address").getAsString());
        ioUnit.setDescription(jsonObject.get("description").isJsonNull() ? "" : jsonObject.get("description").getAsString());
        ioUnit.setSymbol(jsonObject.get("symbol").isJsonNull() ? "" : jsonObject.get("symbol").getAsString());

        return ioUnit;
    }


}
