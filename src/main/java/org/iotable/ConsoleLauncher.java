package org.iotable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.codegenerators.simple.SimpleAiCodeGenerator;
import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.model.ioUnits.typeadapters.AnalogInputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.AnalogOutputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.DiscreteInputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.DiscreteOutputTypeAdapter;
import org.iotable.core.normalize.validation.simple.SimpleAiValidator;
import org.iotable.core.normalize.validation.simple.SimpleAoValidator;
import org.iotable.core.normalize.validation.simple.SimpleDiValidator;
import org.iotable.core.normalize.validation.simple.SimpleDoValidator;
import org.iotable.core.document.XlsxIoTable;

import java.io.*;


public class ConsoleLauncher {

    public static void main(String[] args) throws IOException, InvalidFormatException, TemplateStringException {

        FileInputStream inputStream = new FileInputStream("D:\\ioTables\\jOrjIoTable.xlsx");

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

        XlsxIoTable xlsxIoTable = new XlsxIoTable(xssfWorkbook);

        String json = xlsxIoTable.getAsJsonString();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AnalogInput.class, new AnalogInputTypeAdapter())
                .registerTypeAdapter(DiscreteInput.class, new DiscreteInputTypeAdapter())
                .registerTypeAdapter(AnalogOutput.class, new AnalogOutputTypeAdapter())
                .registerTypeAdapter(DiscreteOutput.class, new DiscreteOutputTypeAdapter())
                .setPrettyPrinting()
                .create();

        IoTable ioTable = gson.fromJson(json, IoTable.class);

        System.out.println(gson.toJson(ioTable));

        IoTable validTable = new IoTable(
                new SimpleAiValidator().validate(ioTable.getAnalogInputs()),
                new SimpleDiValidator().validate(ioTable.getDiscreteInputs()),
                new SimpleAoValidator().validate(ioTable.getAnalogOutputs()),
                new SimpleDoValidator().validate(ioTable.getDiscreteOutputs()));

        System.out.println(gson.toJson(validTable));

        String template = "%description% - %engUnits% - %symbol% - %number% - %address%;";
        new SimpleAiCodeGenerator().generateCode(validTable.getAnalogInputs(), template).stream().forEach(System.out::println);

    }
}