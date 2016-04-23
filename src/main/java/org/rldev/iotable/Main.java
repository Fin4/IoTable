package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.JsonParser;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.rldev.iotable.exceptions.NoSuchSheetException;
import org.rldev.iotable.parsers.XlsxIoTableParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException, InvalidFormatException {

        FileInputStream inputStream = new FileInputStream("D:\\iotable.xlsx");
        String json = new XlsxIoTableParser().parse(inputStream);

/*        File file = new File("D:\\iotable.xlsx");
        String json = new XlsxIoTableParser().parse(file);*/

        Gson gson = new Gson();

        new JsonParser()
                .parse(json)
                .getAsJsonObject()
                .get("digitalInputs")
                .getAsJsonArray()
                .forEach(jsonElement -> {
                    IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                    System.out.println(unit);
                });
    }
}