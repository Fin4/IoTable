package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.mechanisms.SimpleMechanismsParser;
import org.rldev.iotable.model.IoTable;
import org.rldev.iotable.model.ioUnits.*;
import org.rldev.iotable.model.ioUnits.typeadapters.*;
import org.rldev.iotable.model.mechanisms.Mechanism;
import org.rldev.iotable.document.XlsxIoTable;
import org.rldev.iotable.normalize.IoUnitSimpleValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException, WrongFormatException {

        FileInputStream inputStream = new FileInputStream("D:\\ioTables\\ioTable1.xlsx");

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

        XlsxIoTable xlsxIoTable = new XlsxIoTable(xssfWorkbook);

        //xlsxIoTable.info();

        String json = xlsxIoTable.getAsJsonString();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AnalogInput.class, new AnalogInputTypeAdapter())
                .registerTypeAdapter(DigitalInput.class, new DigitalInputTypeAdapter())
                .registerTypeAdapter(AnalogOutput.class, new AnalogOutputTypeAdapter())
                .registerTypeAdapter(DigitalOutput.class, new DigitalOutputTypeAdapter())
                .create();

        IoTable ioTable = gson.fromJson(json, IoTable.class);

        new IoUnitSimpleValidator(new IoUnitSimpleValidator()).validate(ioTable.getAnalogInputs());
        new IoUnitSimpleValidator().validate(ioTable.getDigitalInputs());
        new IoUnitSimpleValidator().validate(ioTable.getAnalogOutputs());
        new IoUnitSimpleValidator().validate(ioTable.getDigitalOutputs());

        //System.out.println(ioTable.getAnalogInputs());

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(ioTable));

        Gson mGson = new GsonBuilder().setPrettyPrinting().create();

        List<IoUnit> ioUnits = new ArrayList<>();
        ioUnits.addAll(ioTable.getDigitalInputs());
        ioUnits.addAll(ioTable.getAnalogInputs());
        ioUnits.addAll(ioTable.getDigitalOutputs());
        ioUnits.addAll(ioTable.getAnalogOutputs());

        List<Mechanism> mechanisms = new SimpleMechanismsParser().getBySymbol(ioUnits)
                .stream().filter(mechanism -> mechanism.getIoUnits().size() >= 2).collect(Collectors.toList());

        mechanisms.forEach(mechanism -> System.out.println(mechanism.getSymbol() + " " + mechanism.getDescription()));

    }
}