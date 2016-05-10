package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.codegenerators.schneider.unitypro.AnalogInputsCodeGenerator;
import org.rldev.iotable.model.IoUnit;
import org.rldev.iotable.model.typeadapters.NullIoUnitTypeAdapter;
import org.rldev.iotable.parsers.XlsxIoTableParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException, WrongFormatException {

        FileInputStream inputStream = new FileInputStream("D:\\iotable.xlsx");
        String json = new XlsxIoTableParser().parse(inputStream);

        System.out.println(json);

        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

        ArrayList<IoUnit> analogInputs = new ArrayList<>();
        ArrayList<IoUnit> digitalInputs = new ArrayList<>();
        ArrayList<IoUnit> analogOutputs = new ArrayList<>();
        ArrayList<IoUnit> digitalOutputs = new ArrayList<>();

        Runnable aiCalc = () -> {
            new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("analogInputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                        analogInputs.add(unit);
                    });
            String template = "fb_ai (inp := %IW%address%, chErr := %IW%address%.ERR, params := ai_%symbol%); (* %symbol% - %description% *)";
            try {
                System.out.println(new AnalogInputsCodeGenerator().generateCode(analogInputs, template));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        };

        Runnable diCalc = () -> {
            new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("digitalInputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                        digitalInputs.add(unit);
                    });
            String template = "di[%number%].inp := %I%address%;";
            try {
                System.out.println(new AnalogInputsCodeGenerator().generateCode(digitalInputs, template));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        };

        Runnable doCalc = () -> {
            new JsonParser()
                        .parse(json)
                        .getAsJsonObject()
                        .get("digitalOutputs")
                        .getAsJsonArray()
                        .forEach(jsonElement -> {
                            IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                            digitalOutputs.add(unit);
                        });
            String template = "%Q%address% := do[%number%].q";
            try {
                System.out.println(new AnalogInputsCodeGenerator().generateCode(digitalOutputs, template));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        };

        Runnable aoCalc = () -> {
            new JsonParser()
                        .parse(json)
                        .getAsJsonObject()
                        .get("analogOutputs")
                        .getAsJsonArray()
                        .forEach(jsonElement -> {
                            IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                            analogOutputs.add(unit);
                        });
            String template = "%QW%address% := ao[%number%].q_iOut;";
            try {
                System.out.println(new AnalogInputsCodeGenerator().generateCode(analogOutputs, template));
            } catch (WrongFormatException | IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(aiCalc).start();
        new Thread(diCalc).start();
        new Thread(aoCalc).start();
        new Thread(doCalc).start();
    }
}