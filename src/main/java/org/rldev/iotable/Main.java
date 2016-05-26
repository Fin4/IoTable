package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.codegenerators.schneider.unitypro.IOCodeGenerator;
import org.rldev.iotable.mechanisms.SimpleMechanismsParser;
import org.rldev.iotable.model.ioUnits.*;
import org.rldev.iotable.model.ioUnits.typeadapters.NullIoUnitTypeAdapter;
import org.rldev.iotable.model.mechanisms.Mechanism;
import org.rldev.iotable.parsers.XlsxIoTableParser;
import org.rldev.iotable.validators.IoUnitsValidator.AiSimpleValidator;
import org.rldev.iotable.validators.IoUnitsValidator.IoUnitSimpleValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException, WrongFormatException {

        FileInputStream inputStream = new FileInputStream("D:\\iotable.xlsx");
        String json = new XlsxIoTableParser().parse(inputStream);

        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

        ArrayList<AnalogInput> analogInputs = new ArrayList<>();
        ArrayList<DigitalInput> digitalInputs = new ArrayList<>();
        ArrayList<AnalogOutput> analogOutputs = new ArrayList<>();
        ArrayList<DigitalOutput> digitalOutputs = new ArrayList<>();

        ArrayList<IoUnit> ioUnits = new ArrayList<>();

        new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("analogInputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        AnalogInput ai = gson.fromJson(jsonElement, AnalogInput.class);
                        analogInputs.add(ai);
                    });

        List<AnalogInput> validAi = new AiSimpleValidator().validate(analogInputs);
        ioUnits.addAll(validAi);

        new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("digitalInputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        DigitalInput di = gson.fromJson(jsonElement, DigitalInput.class);
                        digitalInputs.add(di);
                    });

        List<DigitalInput> validDi = (List<DigitalInput>) new IoUnitSimpleValidator().validate(digitalInputs);
        ioUnits.addAll(validDi);

        new JsonParser()
                        .parse(json)
                        .getAsJsonObject()
                        .get("digitalOutputs")
                        .getAsJsonArray()
                        .forEach(jsonElement -> {
                            DigitalOutput digitalOutput = gson.fromJson(jsonElement, DigitalOutput.class);
                            digitalOutputs.add(digitalOutput);
                        });

        List<DigitalOutput> validDo = (List<DigitalOutput>) new IoUnitSimpleValidator().validate(digitalOutputs);
        ioUnits.addAll(validDo);

        new JsonParser()
                        .parse(json)
                        .getAsJsonObject()
                        .get("analogOutputs")
                        .getAsJsonArray()
                        .forEach(jsonElement -> {
                            AnalogOutput analogOutput = gson.fromJson(jsonElement, AnalogOutput.class);
                            analogOutputs.add(analogOutput);
                        });

        List<AnalogOutput> validAo = (List<AnalogOutput>) new IoUnitSimpleValidator().validate(analogOutputs);
        ioUnits.addAll(validAo);

        Gson mGson = new GsonBuilder().setPrettyPrinting().create();

        List<Mechanism> mechanisms = new SimpleMechanismsParser().getMechanisms(ioUnits);

        /*mechanisms.stream().filter(mechanism -> mechanism.getSymbol().contains("K"))
                .forEach(mechanism -> mechanism.getIoUnits().stream().filter(ioUnit -> ioUnit.getClass() == AnalogInput.class).forEach(System.out::println));*/

        System.out.println(mGson.toJson(mechanisms));
    }
}