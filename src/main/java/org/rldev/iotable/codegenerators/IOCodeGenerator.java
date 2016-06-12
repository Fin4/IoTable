package org.rldev.iotable.codegenerators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.codegenerators.CodeGenerator;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.model.ioUnits.typeadapters.NullIoUnitTypeAdapter;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class IOCodeGenerator implements CodeGenerator {

    private Properties props;

    @Override
    public String generateCode(String aiJsonArray, String template) throws WrongFormatException, IOException {

        ArrayList<IoUnit> ioUnits = new ArrayList<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

        new JsonParser()
                .parse(aiJsonArray)
                .getAsJsonArray()
                .forEach(jsonElement -> ioUnits.add(gson.fromJson(jsonElement, IoUnit.class)));

        return generateCode(ioUnits, template);
    }

    @Override
    public String generateCode(Collection<? extends IoUnit> ioUnits, String template)
            throws WrongFormatException, IOException {

        StringBuilder resultString = new StringBuilder();

        getProperties();

        if (!props.values().stream().anyMatch(o -> template.contains(o.toString()))) throw new WrongFormatException();

        ioUnits.stream().map(ioUnit -> template
                    .replace(props.getProperty("description"), ioUnit.getDescription())
                    .replace(props.getProperty("symbol"), ioUnit.getSymbol().replaceAll(".+\\-", "").replaceAll("\\.", "_"))
                    .replace(props.getProperty("address"), ioUnit.getAddress().replaceAll("/", "\\."))
                    .replace(props.getProperty("number"), String.valueOf(ioUnit.getNumber())))
                .forEach(s -> resultString.append(s).append(System.lineSeparator()));

        return new String(resultString);
    }

    private void getProperties() throws IOException {

        props = new Properties();

        InputStream inputStream = new FileInputStream("src/main/resources/IoUnitsCode.properties");

        props.load(inputStream);
    }
}
