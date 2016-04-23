package org.rldev.iotable.parsers;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rldev.iotable.exceptions.NoSuchSheetException;
import org.rldev.iotable.exceptions.WrongSheetFormatException;

import java.io.File;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;



/** An implementation of #IoTableParser interface
 * Class contains methods to parse excel documents 2007 and later versions.
 * <b>maker</b> и <b>price</b>.
 * @author Roman Lychak
 * @version 1.0
 * @since JDK 1.8
 */

public class XlsxIoTableParser implements IoTableParser {

    @Override
    public String parse(InputStream inputStream) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);

        return parseIoTable(workbook).toString();
    }

    @Override
    public String parse(File file) throws IOException, InvalidFormatException {

        XSSFWorkbook workbook = new XSSFWorkbook(file);

        return parseIoTable(workbook).toString();
    }

    private JsonArray parseIoUnitsSheet(XSSFSheet sheet) throws WrongSheetFormatException {

        ArrayList<String> headers = new ArrayList<>();

        Row headerRow = sheet.getRow(0);

        if (headerRow == null || headerRow.getPhysicalNumberOfCells() <= 0)
            throw new WrongSheetFormatException(sheet.getSheetName() + " sheet: first row (header) is null");

        headerRow.forEach(cell -> headers.add(cell.toString().toLowerCase()));

        sheet.removeRow(headerRow);

        JsonArray jsonArray = new JsonArray();

        sheet.forEach(row -> {
            JsonObject jsonObject = new JsonObject();

            row.forEach(cell -> jsonObject.addProperty(headers.get(cell.getColumnIndex()), cell.toString()));

            jsonArray.add(jsonObject);
        });

        return jsonArray;
    }

    private JsonObject parseIoTable(XSSFWorkbook workbook) {

        XSSFSheet diSheet = workbook.getSheet("DI");
        XSSFSheet aiSheet = workbook.getSheet("AI");
        XSSFSheet doSheet = workbook.getSheet("DO");
        XSSFSheet aoSheet = workbook.getSheet("AO");

        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.add("digitalInputs", parseIoUnitsSheet(diSheet));
            jsonObject.add("analogInputs", parseIoUnitsSheet(aiSheet));
            jsonObject.add("digitalOutputs", parseIoUnitsSheet(doSheet));
            jsonObject.add("analogOutputs", parseIoUnitsSheet(aoSheet));
        } catch (WrongSheetFormatException e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
