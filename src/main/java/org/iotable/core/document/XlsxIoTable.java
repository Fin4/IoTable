package org.iotable.core.document;


import com.google.gson.JsonArray;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.iotable.core.document.exceptions.WrongSheetFormatException;

import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/** An implementation of #IoTableDocument interface
 * Class contains methods to getAsJsonString excel documents 2007 and later versions.
 * @author Roman Lychak
 * @version 1.0
 * @since JDK 1.8
 */

public final class XlsxIoTable implements IoTableDocument {

    private final Workbook workbook;

    private Properties props;

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

            headers.stream().forEach(s -> {
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

        if (props == null) {
            logger.info("Properties not found. Loading default properties...");
            defaultProps();
        }

        Sheet diSheet = workbook.getSheet(props.getProperty("diSheetName"));
        Sheet aiSheet = workbook.getSheet(props.getProperty("aiSheetName"));
        Sheet doSheet = workbook.getSheet(props.getProperty("doSheetName"));
        Sheet aoSheet = workbook.getSheet(props.getProperty("aoSheetName"));

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

        Thread doThread = new Thread(() -> {
            try {
                jsonObject.add("discreteOutputs", parseIoUnitsSheet(doSheet));
            } catch (WrongSheetFormatException e) {
                e.printStackTrace();
                jsonObject.add("discreteOutputs", new JsonObject());
            }
        });

        Thread aoThread = new Thread(() -> {
            try {
                jsonObject.add("analogOutputs", parseIoUnitsSheet(aoSheet));
            } catch (WrongSheetFormatException e) {
                e.printStackTrace();
                jsonObject.add("analogOutputs", new JsonObject());
            }
        });

        diThread.start();
        //diThread.setDaemon(true);
        aiThread.start();
        //aiThread.setDaemon(true);
        doThread.start();
        //doThread.setDaemon(true);
        aoThread.start();
        //aoThread.setDaemon(true);

        try {
            diThread.join();
            aiThread.join();
            doThread.join();
            aoThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
            logger.error("can't parse iotable cause: " + e);
        }

        return jsonObject;
    }

    private void defaultProps() {

        props = new Properties();

        props.setProperty("diSheetName" , "DI");
        props.setProperty("aiSheetName" , "AI");
        props.setProperty("doSheetName" , "DO");
        props.setProperty("aoSheetName" , "AO");

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