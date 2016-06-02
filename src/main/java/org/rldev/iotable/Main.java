package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.codegenerators.schneider.unitypro.IOCodeGenerator;
import org.rldev.iotable.mechanisms.SimpleMechanismsParser;
import org.rldev.iotable.model.IoTable;
import org.rldev.iotable.model.ioUnits.*;
import org.rldev.iotable.model.ioUnits.typeadapters.*;
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

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AnalogInput.class, new AnalogInputTypeAdapter())
                .registerTypeAdapter(DigitalInput.class, new DigitalInputTypeAdapter())
                .registerTypeAdapter(AnalogOutput.class, new AnalogOutputTypeAdapter())
                .registerTypeAdapter(DigitalOutput.class, new DigitalOutputTypeAdapter())
                .create();

        IoTable ioTable = gson.fromJson(json, IoTable.class);

        new AiSimpleValidator().validate(ioTable.getAnalogInputs());
        new IoUnitSimpleValidator().validate(ioTable.getDigitalInputs());
        new IoUnitSimpleValidator().validate(ioTable.getAnalogOutputs());
        new IoUnitSimpleValidator().validate(ioTable.getDigitalOutputs());

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(ioTable));

        Gson mGson = new GsonBuilder().setPrettyPrinting().create();

        List<IoUnit> ioUnits = new ArrayList<>();
        ioUnits.addAll(ioTable.getDigitalInputs());
        ioUnits.addAll(ioTable.getAnalogInputs());
        ioUnits.addAll(ioTable.getDigitalOutputs());
        ioUnits.addAll(ioTable.getAnalogOutputs());

        List<Mechanism> mechanisms = new SimpleMechanismsParser().getMechanisms(ioUnits)
                .stream().filter(mechanism -> mechanism.getIoUnits().size() >= 1).collect(Collectors.toList());

        System.out.println(mGson.toJson(mechanisms));

    }
}