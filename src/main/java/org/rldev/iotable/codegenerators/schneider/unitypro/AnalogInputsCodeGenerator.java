package org.rldev.iotable.codegenerators.schneider.unitypro;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.rldev.iotable.model.IoUnit;
import org.rldev.iotable.codegenerators.CodeGenerator;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;

public class AnalogInputsCodeGenerator implements CodeGenerator {

    private Properties props;

    @Override
    public String generateCode(String aiJsonArray, String template) throws WrongFormatException, IOException {

        ArrayList<IoUnit> ioUnits = new ArrayList<>();

        Gson gson = new Gson();

        new JsonParser()
                .parse(aiJsonArray)
                .getAsJsonArray()
                .forEach(jsonElement -> ioUnits.add(gson.fromJson(jsonElement, IoUnit.class)));

        return generateCode(ioUnits, template);
    }

    @Override
    public String generateCode(Collection<? extends IoUnit> analogInputs, String template)
            throws WrongFormatException, IOException {

        StringBuffer resultString = new StringBuffer();

        getProperties();

        if (!props.values().stream().anyMatch(o -> template.contains(o.toString()))) throw new WrongFormatException();

        analogInputs.stream().map(ioUnit -> template
                    .replace(props.getProperty("description"), ioUnit.getDescription())
                    .replace(props.getProperty("symbol"), ioUnit.getSymbol())
                    .replace(props.getProperty("address"), ioUnit.getAddress())
                    .replace(props.getProperty("number"), String.valueOf(ioUnit.getNumber())))
                .forEach(s -> resultString.append(s).append(System.lineSeparator()));


        return new String(resultString);
    }

    private void getProperties() throws IOException {

        props = new Properties();

        InputStream inputStream = new FileInputStream("src/main/resources/analogInputsCode.properties");

        props.load(inputStream);
    }
}
