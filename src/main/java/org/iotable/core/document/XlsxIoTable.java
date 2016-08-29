package org.iotable.core.document;


import com.google.gson.*;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.iotable.core.Config;
import org.iotable.core.document.exceptions.WrongSheetFormatException;
import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.ArrayList;


/** An implementation of #IoTableDocument interface
 * Class contains methods to getAsJsonString excel documents 2007 and later versions.
 * @author Roman Lychak
 * @version 1.0
 * @since JDK 1.8
 */

public final class XlsxIoTable implements IoTableDocument {

    private final Workbook workbook;

    private static final Logger logger = Logger.getLogger(XlsxIoTable.class);

    public XlsxIoTable(Workbook workbook) {
        this.workbook = workbook;
    }

    @Override
    public String getAsJsonString() {

        return parseIoTable(workbook).toString();
    }

    private JsonArray parseIoUnitsSheet(final Sheet sheet) throws WrongSheetFormatException {

        final String sheetName = sheet.getSheetName();

        logger.info(sheetName + " sheet: start parsing...");

        ArrayList<String> headers = new ArrayList<>();

        Row headerRow = sheet.getRow(0);

        if (headerRow == null || headerRow.getPhysicalNumberOfCells() <= 0) {
            logger.error(sheetName + " sheet: Wrong format header row: header row is empty");
            throw new WrongSheetFormatException(sheetName + " sheet: first row (header) is null");
        }

        headerRow.forEach(cell -> {
            cell.setCellType(Cell.CELL_TYPE_STRING);
            if (!cell.getStringCellValue().trim().isEmpty()) {
                headers.add(cell.getStringCellValue());
                logger.info(sheetName + " sheet: add header cell " + "'" + headers.get(headers.size() - 1) + "'");
            }
        });

        JsonArray jsonArray = new JsonArray();

        for (int i = 1;; i++) {

            Row row = sheet.getRow(i);
            Cell firstCell;

            if (row == null || row.getCell(0) == null) break;
            else {
                firstCell = row.getCell(0);
                firstCell.setCellType(Cell.CELL_TYPE_STRING);
                if (firstCell.getStringCellValue().trim().isEmpty()) break;
            }

            JsonObject jsonObject = new JsonObject();

            headers.forEach(s -> {
                Cell cell = row.getCell(headers.indexOf(s));
                if (cell == null) jsonObject.add(s, JsonNull.INSTANCE);
                else {
                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    jsonObject.addProperty(headers.get(cell.getColumnIndex()), cell.getStringCellValue());
                }
            });
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }

    private JsonObject parseIoTable(final Workbook workbook) {

        Sheet diSheet = workbook.getSheet(Config.getProperty("sheet.di"));
        Sheet aiSheet = workbook.getSheet(Config.getProperty("sheet.ai"));
        Sheet doSheet = workbook.getSheet(Config.getProperty("sheet.do"));
        Sheet aoSheet = workbook.getSheet(Config.getProperty("sheet.ao"));

        JsonObject jsonObject = new JsonObject();

        Thread diThread = new Thread(() -> {
            try {
                jsonObject.add("discreteInputs", parseIoUnitsSheet(diSheet));
            } catch (WrongSheetFormatException e) {
                e.printStackTrace();
                jsonObject.add("discreteInputs", new JsonObject());
            }
        });
        diThread.setName("diSheet-parsing");

        Thread aiThread = new Thread(() -> {
            try {
                jsonObject.add("analogInputs", parseIoUnitsSheet(aiSheet));
            } catch (WrongSheetFormatException e) {
                e.printStackTrace();
                jsonObject.add("analogInputs", new JsonObject());
            }
        });
        aiThread.setName("aiSheet-parsing");

        Thread doThread = new Thread(() -> {
            try {
                jsonObject.add("discreteOutputs", parseIoUnitsSheet(doSheet));
            } catch (WrongSheetFormatException e) {
                e.printStackTrace();
                jsonObject.add("discreteOutputs", new JsonObject());
            }
        });
        doThread.setName("doSheet-parsing");

        Thread aoThread = new Thread(() -> {
            try {
                jsonObject.add("analogOutputs", parseIoUnitsSheet(aoSheet));
            } catch (WrongSheetFormatException e) {
                e.printStackTrace();
                jsonObject.add("analogOutputs", new JsonObject());
            }
        });
        aoThread.setName("aoSheet-parsing");

        diThread.start();
        aiThread.start();
        doThread.start();
        aoThread.start();

        try {
            diThread.join();
            aiThread.join();
            doThread.join();
            aoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("can't parse iotable: ", e);
        }

        return jsonObject;
    }

    @Override
    public IoTable getAsIoTable() {

        String json = getAsJsonString();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(AnalogInput.class, new AnalogInputTypeAdapter())
                .registerTypeAdapter(DiscreteInput.class, new DiscreteInputTypeAdapter())
                .registerTypeAdapter(AnalogOutput.class, new AnalogOutputTypeAdapter())
                .registerTypeAdapter(DiscreteOutput.class, new DiscreteOutputTypeAdapter())
                .create();

        return gson.fromJson(json, IoTable.class);
    }

    public String info() {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Sheets: ").append(System.lineSeparator());

        workbook.forEach(sheet -> stringBuilder
                .append("name : ")
                .append(sheet.getSheetName())
                .append(System.lineSeparator()).append("num of rows : ")
                .append(sheet.getPhysicalNumberOfRows())
                .append(System.lineSeparator()));

        return new String(stringBuilder);
    }
}