/*

package org.iotable.core;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.model.ioUnits.typeadapters.AnalogInputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.AnalogOutputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.DiscreteInputTypeAdapter;
import org.iotable.core.model.ioUnits.typeadapters.DiscreteOutputTypeAdapter;
import org.iotable.core.normalize.validation.AiSimpleValidator;
import org.iotable.core.normalize.validation.AoSimpleValidator;
import org.iotable.core.normalize.validation.DiSimpleValidator;
import org.iotable.core.normalize.validation.DoSimpleValidator;
import org.iotable.core.document.XlsxIoTable;

import java.io.*;


public class Main {

    public static void main(String[] args) throws IOException, InvalidFormatException, TemplateStringException {

        FileInputStream inputStream = new FileInputStream("D:\\ioTables\\jOrjIoTable.xlsx");

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);

        XlsxIoTable xlsxIoTable = new XlsxIoTable(xssfWorkbook);

        //xlsxIoTable.info();

        String json = xlsxIoTable.getAsJsonString();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AnalogInput.class, new AnalogInputTypeAdapter())
                .registerTypeAdapter(DiscreteInput.class, new DiscreteInputTypeAdapter())
                .registerTypeAdapter(AnalogOutput.class, new AnalogOutputTypeAdapter())
                .registerTypeAdapter(DiscreteOutput.class, new DiscreteOutputTypeAdapter())
                .create();

        IoTable ioTable = gson.fromJson(json, IoTable.class);

        new AiSimpleValidator().validate(ioTable.getAnalogInputs());
        new DiSimpleValidator().validate(ioTable.getDiscreteInputs());
        new AoSimpleValidator().validate(ioTable.getAnalogOutputs());
        new DoSimpleValidator().validate(ioTable.getDiscreteOutputs());

        //System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(ioTable));
        //System.out.println(new SimpleCodeGenerator().generateCode(ioTable.getAnalogInputs(), "%number% + %address% %description% \n"));

        Gson mGson = new GsonBuilder().setPrettyPrinting().create();

*/
/*        List<IoUnit> ioUnits = new ArrayList<>();
        ioUnits.addAll(ioTable.getDiscreteInputs());
        ioUnits.addAll(ioTable.getAnalogInputs());
        ioUnits.addAll(ioTable.getDiscreteOutputs());
        ioUnits.addAll(ioTable.getAnalogOutputs());

        List<Mechanism> mechanisms = new SimpleMechanismsParser().getBySymbol(ioUnits)
                .stream().filter(mechanism -> mechanism.getIoUnits().size() >= 2).collect(Collectors.toList());

        mechanisms.forEach(mechanism -> System.out.println(mechanism.getSymbol() + " " + mechanism.getDescription()));*//*


    }
}
*/
