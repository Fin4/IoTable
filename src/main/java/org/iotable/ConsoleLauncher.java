package org.iotable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.iotable.core.document.IoTableDocument;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.mappers.simple.SimpleAiMapper;
import org.iotable.core.mechanisms.mappers.MechanismMapper;
import org.iotable.core.mechanisms.mappers.SimpleDgMapper;
import org.iotable.core.mechanisms.SimpleMechanismsParser;
import org.iotable.core.model.IoTable;
import org.iotable.core.model.mechanisms.Mechanism;
import org.iotable.core.normalize.validation.simple.SimpleAiValidator;
import org.iotable.core.normalize.validation.simple.SimpleAoValidator;
import org.iotable.core.normalize.validation.simple.SimpleDiValidator;
import org.iotable.core.normalize.validation.simple.SimpleDoValidator;
import org.iotable.core.document.XlsxIoTable;

import java.io.*;
import java.util.List;


public class ConsoleLauncher {

    public static void main(String[] args) throws IOException, InvalidFormatException, TemplateStringException {

        FileInputStream inputStream = new FileInputStream("E:\\Оржица\\orjIoTable29_06_2016.xlsx");

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

        IoTableDocument xlsxIoTable = new XlsxIoTable(xssfWorkbook);

        IoTable ioTable = xlsxIoTable.getAsIoTable();

        //System.out.println(gson.toJson(ioTable));

        IoTable validTable = new IoTable(
                new SimpleAiValidator().validate(ioTable.getAnalogInputs()),
                new SimpleDiValidator().validate(ioTable.getDiscreteInputs()),
                new SimpleAoValidator().validate(ioTable.getAnalogOutputs()),
                new SimpleDoValidator().validate(ioTable.getDiscreteOutputs()));

        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(validTable));

        String template = "%desc% - %eu% - %symbol% - %num% - %addr%;";
        new SimpleAiMapper().generateCode(validTable.getAnalogInputs(), template).forEach(System.out::println);

        Gson mGson = new GsonBuilder().setPrettyPrinting().create();

        List<Mechanism> mechanisms = new SimpleMechanismsParser().getBySymbol(validTable);
        //System.out.println(mGson.toJson(mechanisms));
        List<Mechanism> valves = new SimpleMechanismsParser().getByType("J1-Z", mechanisms);


        MechanismMapper mapper = new SimpleDgMapper();
        valves.forEach(valve -> System.out.println(mapper
                .map("%symbol%.i_open := %opened%.val;\n" +
                        "%symbol%.i_close := %closed%.val;\n" +
                        "do.%close%.i_val := %symbol%.q_close;\n", valve)));

        System.out.println(mGson.toJson(valves));
    }
}