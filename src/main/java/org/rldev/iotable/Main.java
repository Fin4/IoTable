package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.codegenerators.schneider.unitypro.IOCodeGenerator;
import org.rldev.iotable.mechanisms.SimpleMechanismsParser;
import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.model.ioUnits.typeadapters.NullIoUnitTypeAdapter;
import org.rldev.iotable.parsers.XlsxIoTableParser;
import org.rldev.iotable.validators.IoUnitsValidator.AiSimpleValidator;
import org.rldev.iotable.validators.IoUnitsValidator.IoUnitSimpleValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException, WrongFormatException {

        FileInputStream inputStream = new FileInputStream("D:\\iotable.xlsx");
        String json = new XlsxIoTableParser().parse(inputStream);


        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

        ArrayList<IoUnit> analogInputs = new ArrayList<>();
        ArrayList<IoUnit> digitalInputs = new ArrayList<>();
        ArrayList<IoUnit> analogOutputs = new ArrayList<>();
        ArrayList<IoUnit> digitalOutputs = new ArrayList<>();

        ArrayList<IoUnit> ioUnits = new ArrayList<IoUnit>();


        //Runnable aiCalc = () -> {
            new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("analogInputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        IoUnit unit = gson.fromJson(jsonElement, AnalogInput.class);
                        analogInputs.add(unit);
                    });
            String aiTemplate = "fb_ai (inp := %IW%address%, chErr := %IW%address%.ERR, params := ai_%symbol%); (* %symbol% - %description% *)";

            try {
                List<AnalogInput> validAi = new AiSimpleValidator().validate(analogInputs);

                ioUnits.addAll(validAi);

                System.out.println(new IOCodeGenerator().generateCode(validAi, aiTemplate));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        //};

        //Runnable diCalc = () -> {
            new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("digitalInputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                        digitalInputs.add(unit);
                    });
            String diTemplate = "di[%number%].i_value := %I%address%; di[%number%].q_chErr := %I%address%.ERR; (* %symbol% - %description% *)";
            try {
                List<IoUnit> validDi = (List<IoUnit>) new IoUnitSimpleValidator().validate(digitalInputs);

                ioUnits.addAll(validDi);

                System.out.println(new IOCodeGenerator().generateCode(validDi, diTemplate));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        //};

        //Runnable doCalc = () -> {
            new JsonParser()
                        .parse(json)
                        .getAsJsonObject()
                        .get("digitalOutputs")
                        .getAsJsonArray()
                        .forEach(jsonElement -> {
                            IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                            digitalOutputs.add(unit);
                        });
            String doTemplate = "%Q%address% := do[%number%].q; (* %symbol% - %description% *)";
            try {
                List<IoUnit> validDo = (List<IoUnit>) new IoUnitSimpleValidator().validate(digitalOutputs);

                ioUnits.addAll(validDo);

                System.out.println(new IOCodeGenerator().generateCode(validDo, doTemplate));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        //};

        //Runnable aoCalc = () -> {
            new JsonParser()
                        .parse(json)
                        .getAsJsonObject()
                        .get("analogOutputs")
                        .getAsJsonArray()
                        .forEach(jsonElement -> {
                            IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                            analogOutputs.add(unit);
                        });
            String aoTemplate = "%QW%address% := ao[%number%].q_iOut; (* %symbol% - %description% *)";
            try {
                List<IoUnit> validAo = (List<IoUnit>) new IoUnitSimpleValidator().validate(analogOutputs);

                ioUnits.addAll(validAo);

                System.out.println(new IOCodeGenerator().generateCode(validAo, aoTemplate));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        //};


/*        Map<String, List<IoUnit>> mechanisms = ioUnits.stream().collect(Collectors.groupingBy(ioUnit -> {

            String symbol = ioUnit.getSymbol();

            if (symbol.matches(".+\\.[a-zA-Z0-9]+")) return symbol.substring(0, symbol.lastIndexOf("."));
            else return symbol;
        }
        ));*/

        Gson mGson = new Gson();

        System.out.println(mGson.toJson(new SimpleMechanismsParser().getMechanisms(ioUnits)));
    }
}