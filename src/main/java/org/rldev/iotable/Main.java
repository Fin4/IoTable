package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.rldev.iotable.codegenerators.exceptions.WrongFormatException;
import org.rldev.iotable.codegenerators.schneider.unitypro.AnalogInputsCodeGenerator;
import org.rldev.iotable.model.IoUnit;
import org.rldev.iotable.parsers.XlsxIoTableParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException {

        FileInputStream inputStream = new FileInputStream("D:\\iotable.xlsx");
        String json = new XlsxIoTableParser().parse(inputStream);

/*        File file = new File("D:\\iotable.xlsx");
        String json = new XlsxIoTableParser().parse(file);*/

        System.out.println(json);

        Gson gson = new Gson();

        ArrayList<IoUnit> ioUnits = new ArrayList<>();

        new JsonParser()
                .parse(json)
                .getAsJsonObject()
                .get("analogInputs")
                .getAsJsonArray()
                .forEach(jsonElement -> {
                    IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                    ioUnits.add(unit);
                });

        try {
            String template = "fb_ai (inp := %IW%addr%, chErr := %IW%addr%.ERR, params := ai_%symbol%); (* %symbol% - %desc% *)";
            System.out.println(new AnalogInputsCodeGenerator()
                    .generateCode(ioUnits, template));
        } catch (WrongFormatException e) {
            e.printStackTrace();
        }


    }
}