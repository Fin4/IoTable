package org.rldev.iotable.parsers;


import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rldev.iotable.parsers.exceptions.WrongSheetFormatException;

import java.io.File;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;


/** An implementation of #IoTableParser interface
 * Class contains methods to parse excel documents 2007 and later versions.
 * @author Roman Lychak
 * @version 1.0
 * @since JDK 1.8
 */

public class XlsxIoTableParser implements IoTableParser {

    private Properties props;

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

        headerRow.forEach(cell -> {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            headers.add(cell.getStringCellValue().toLowerCase());
        });

        sheet.removeRow(headerRow);

        JsonArray jsonArray = new JsonArray();

        sheet.forEach(row -> {
            JsonObject jsonObject = new JsonObject();

            headers.stream().forEach(s -> {
                Cell cell = row.getCell(headers.indexOf(s));
                if (cell == null) jsonObject.add(s, JsonNull.INSTANCE);
                else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    jsonObject.addProperty(headers.get(cell.getColumnIndex()), cell.getStringCellValue());
                }
            });
            jsonArray.add(jsonObject);
        });

        return jsonArray;
    }

    private JsonObject parseIoTable(XSSFWorkbook workbook) throws IOException {

        getProperties();

        XSSFSheet diSheet = workbook.getSheet(props.getProperty("diSheetName"));
        XSSFSheet aiSheet = workbook.getSheet(props.getProperty("aiSheetName"));
        XSSFSheet doSheet = workbook.getSheet(props.getProperty("doSheetName"));
        XSSFSheet aoSheet = workbook.getSheet(props.getProperty("aoSheetName"));

        JsonObject jsonObject = new JsonObject();

        try {
            jsonObject.add("digitalInputs", parseIoUnitsSheet(diSheet));
        } catch (WrongSheetFormatException e) {
            e.printStackTrace();
            jsonObject.add("digitalInputs", new JsonObject());
        }

        try {
            jsonObject.add("analogInputs", parseIoUnitsSheet(aiSheet));
        } catch (WrongSheetFormatException e) {
            e.printStackTrace();
            jsonObject.add("analogInputs", new JsonObject());
        }

        try {
            jsonObject.add("digitalOutputs", parseIoUnitsSheet(doSheet));
        } catch (WrongSheetFormatException e) {
            e.printStackTrace();
            jsonObject.add("digitalOutputs", new JsonObject());
        }

        try {
            jsonObject.add("analogOutputs", parseIoUnitsSheet(aoSheet));
        } catch (WrongSheetFormatException e) {
            e.printStackTrace();
            jsonObject.add("analogOutputs", new JsonObject());
        }

        return jsonObject;
    }

    private void getProperties() throws IOException {

        props = new Properties();

        InputStream inputStream = new FileInputStream("src/main/resources/XlsxDoc.properties");

        props.load(inputStream);
    }
}