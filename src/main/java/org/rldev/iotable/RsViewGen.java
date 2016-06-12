package org.rldev.iotable;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.model.ioUnits.typeadapters.NullIoUnitTypeAdapter;
import org.rldev.iotable.document.XlsxIoTable;
import org.rldev.iotable.validators.IoUnitsValidator.AiSimpleValidator;
import org.rldev.iotable.validators.IoUnitsValidator.IoUnitSimpleValidator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RsViewGen {

    private static final String HEADER = ";Tag Type, Tag Name, Tag Description, Read Only, Data Source, Security Code, Alarmed, Data Logged, Native Type, Value Type, Min Analog, Max Analog, Initial Analog, Scale, Offset, DeadBand, Units, Off Label Digital, On Label Digital, Initial Digital, Length String, Initial String, Node Name, Address, Scan Class,  System Source Name, System Source Index, RIO Address, Element Size Block, Number Elements Block, Initial Block" + System.lineSeparator() +
            ";###001 - THIS LINE CONTAINS VERSION INFORMATION. DO NOT REMOVE!!!" + System.lineSeparator() +
            "" +
            ";Folders Section (Must define folders before tags)" + System.lineSeparator() +
            "" + System.lineSeparator() +
            ";Tag Section" + System.lineSeparator();

    public static void main(String[] args) throws IOException {

        XSSFWorkbook xssfWorkbook = new XSSFWorkbook(new FileInputStream("D:\\ioTables\\jOrjIoTable.xlsx"));

        String json = new XlsxIoTable(xssfWorkbook).getAsJsonString();

        generateAiTags(json);
        generateDiTags(json);
        generateAoTags(json);
        generateDoTags(json);

    }

    private static void generateAiTags(String json) {

        ArrayList<IoUnit> analogInputs = new ArrayList<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

        Runnable aiCalc = () -> {
            new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("analogInputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        IoUnit unit = gson.fromJson(jsonElement, AnalogInput.class);
                        analogInputs.add(unit);
                    });

            String aiTagsTemplate = "\"D\",\"J2\\AI\\AI%number%\\ChFlt\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Ошибка канала\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\DnScaleFlt\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Обрыв\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"A\",\"J2\\AI\\AI%number%\\EU\",\"%description%\",\"F\",\"D\",\"*\",\"F\",\"F\",\"F\",\"F\",-100000,100000,0,1,0,0,\"mA\",,,,,,\"J2\",\"J2!ai[%number%].q.adc\",\"A\",,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\F1\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Фильтр 1го порядка\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\H\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Верх.регламентная\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\HH\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Верх.аварийная\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\L\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Ниж.регламентная\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\LL\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Ниж.аварийная\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\Sim\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Симуляция сигнала\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"A\",\"J2\\AI\\AI%number%\\State\",\"\",\"F\",\"D\",\"*\",\"F\",\"F\",\"U\",\"L\",0,65538,0,1,0,0,\"\",,,,,,\"J2\",\"J2!ai[%number%].q.state\",\"A\",,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\UpScaleFlt\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Зашкал\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"A\",\"J2\\AI\\AI%number%\\VAL\",\"J2-%symbol%\",\"F\",\"D\",\"*\",\"F\",\"F\",\"F\",\"F\",-100000,100000,0,1,0,0,\"%engUnits%\",,,,,,\"J2\",\"J2!ai[%number%].q.pv\",\"A\",,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AI\\AI%number%\\Valid\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"'Норма'\",\"Off\",,,,,,,,,,," + System.lineSeparator();

            String aiDerivedTagsTemplate = "\"J2\\AI\\AI%number%\\ChFlt\",\"J2\\AI\\AI%number%\\State & 64 == 64\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\UpScaleFlt\",\"J2\\AI\\AI%number%\\State & 32 == 32\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\DnScaleFlt\",\"J2\\AI\\AI%number%\\State & 16 == 16\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\LL\",\"J2\\AI\\AI%number%\\State & 1 == 1\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\L\",\"J2\\AI\\AI%number%\\State & 2 == 2\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\H\",\"J2\\AI\\AI%number%\\State & 4 == 4\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\HH\",\"J2\\AI\\AI%number%\\State & 8 == 8\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\Sim\",\"J2\\AI\\AI%number%\\State & 256 == 256\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\Valid\",\"J2\\AI\\AI%number%\\State & 128 == 128\",\"\",\"AIS\"" + System.lineSeparator() +
                    "\"J2\\AI\\AI%number%\\F1\",\"J2\\AI\\AI%number%\\State & 0 == 0\",\"\",\"AIS\"" + System.lineSeparator();
            try {
                List<AnalogInput> validAis = new AiSimpleValidator().validate(analogInputs);

                File aiFile = new File("ai.csv");
                File derivedAiFile = new File("derivedAi.csv");

                aiFile.createNewFile();
                derivedAiFile.createNewFile();

                /*Writer aiBufferedWriter = new BufferedWriter(new FileWriter(aiFile.getAbsoluteFile()));
                BufferedWriter aiDerivedBufferedWriter = new BufferedWriter(new FileWriter(derivedAiFile.getAbsoluteFile()));*/

                Writer aiBufferedWriter = new OutputStreamWriter(new FileOutputStream(aiFile.getAbsoluteFile()), "windows-1251");
                Writer aiDerivedBufferedWriter = new OutputStreamWriter(new FileOutputStream(derivedAiFile.getAbsoluteFile()), "windows-1251");

                aiBufferedWriter.write(HEADER);
                aiDerivedBufferedWriter.write(HEADER);

                validAis.stream().map(analogInput -> aiTagsTemplate
                        .replace("%number%", String.valueOf(analogInput.getNumber()))
                        .replace("%description%", analogInput.getDescription())
                        .replace("%symbol%", analogInput.getSymbol())
                        .replace("%engUnits%", analogInput.getEngUnits())).forEach(s -> {
                    try {
                        aiBufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                aiBufferedWriter.close();

                validAis.stream().map(analogInput -> aiDerivedTagsTemplate
                        .replace("%number%", String.valueOf(analogInput.getNumber()))
                        .replace("%description%", analogInput.getDescription())
                        .replace("%symbol%", analogInput.getSymbol())
                        .replace("%engUnits%", analogInput.getEngUnits())).forEach(s -> {
                    try {

                        aiDerivedBufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                aiDerivedBufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(aiCalc).start();
    }

    private static void generateDiTags(String json) {

        ArrayList<IoUnit> digitalInputs = new ArrayList<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

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

            String tagsTemplate = "\"A\",\"J2\\DI\\DI%number%\\data\",\"J2-%symbol%\",\"F\",\"D\",\"*\",\"F\",\"F\",\"U\",\"L\",0,65538,0,1,0,0,\"\",,,,,,\"J2\",\"J2!di[%number%].q\",\"A\",,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\DI\\DI%number%\\di\",\"J2-Вход ПЛК\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"=0\",\"=1\",\"=0\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\DI\\DI%number%\\flt\",\"\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Норма\",\"Ошибка\",\"Норма\",,,,,,,,,,," + System.lineSeparator() +
                    "\"A\",\"J2\\DI\\DI%number%\\n\",\"J2-номер\",\"F\",\"M\",\"*\",\"F\",\"F\",\"D\",\"L\",0,1000,%number%,1,0,0,\"\",,,,,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\DI\\DI%number%\\sim\",\"%number%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Вкл\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\DI\\DI%number%\\val\",\"%description%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"=0\",\"=1\",\"=0\",,,,,,,,,,," + System.lineSeparator();

            String derivedTagsTemplate = "\"J2\\DI\\DI%number%\\VAL\",\"J2\\DI\\DI%number%\\data & 1 == 1\",\"\",\"DIS\"" + System.lineSeparator() +
                    "\"J2\\DI\\DI%number%\\FLT\",\"J2\\DI\\DI%number%\\data & 2 == 2\",\"\",\"DIS\"" + System.lineSeparator() +
                    "\"J2\\DI\\DI%number%\\di\",\"J2\\DI\\DI%number%\\data & 32 == 32\",\"\",\"DIS\"" + System.lineSeparator() +
                    "\"J2\\DI\\DI%number%\\SIM\",\"J2\\DI\\DI%number%\\data & 16 == 16\",\"\",\"DIS\"" + System.lineSeparator();
            try {
                List<IoUnit> ioUnits = (List<IoUnit>) new IoUnitSimpleValidator().validate(digitalInputs);

                File file = new File("di.csv");
                File derivedFile = new File("derivedDi.csv");

                file.createNewFile();
                derivedFile.createNewFile();

/*                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                BufferedWriter derivedBufferedWriter = new BufferedWriter(new FileWriter(derivedFile.getAbsoluteFile()));*/

                Writer bufferedWriter = new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "windows-1251");
                Writer derivedBufferedWriter = new OutputStreamWriter(new FileOutputStream(derivedFile.getAbsoluteFile()), "windows-1251");

                bufferedWriter.write(HEADER);
                derivedBufferedWriter.write(HEADER);

                ioUnits.stream().map(ioUnit -> tagsTemplate
                        .replace("%number%", String.valueOf(ioUnit.getNumber()))
                        .replace("%description%", ioUnit.getDescription())
                        .replace("%symbol%", ioUnit.getSymbol())
                        ).forEach(s -> {
                    try {
                        bufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                bufferedWriter.close();

                ioUnits.stream().map(ioUnit -> derivedTagsTemplate
                        .replace("%number%", String.valueOf(ioUnit.getNumber()))
                        .replace("%description%", ioUnit.getDescription())
                        .replace("%symbol%", ioUnit.getSymbol())).
                        forEach(s -> {
                    try {

                       derivedBufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                derivedBufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(diCalc).start();
    }

    private static void generateAoTags(String json) {

        ArrayList<IoUnit> srcIoUnits = new ArrayList<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

        Runnable calculation = () -> {
            new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("analogOutputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                        srcIoUnits.add(unit);
                    });

            String tagsTemplate = "\"A\",\"J2\\AO\\AO%number%\\EU\",\"%description%\",\"F\",\"D\",\"*\",\"F\",\"F\",\"F\",\"F\",-100000,100000,0,1,0,0,\"\",,,,,,\"J2\",\"J2!aq[%number%].i_value\",\"A\",,,,,," + System.lineSeparator() +
                    "\"D\",\"J2\\AO\\AO%number%\\SIM\",\"\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Симулировать параметр\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"A\",\"J2\\AO\\AO%number%\\State\",\"%number%\",\"F\",\"D\",\"*\",\"F\",\"F\",\"D\",\"L\",0,65538,0,1,0,0,\"\",,,,,,\"J2\",\"J2!aq[%number%].settings\",\"A\",,,,,," + System.lineSeparator() +
                    "\"A\",\"J2\\AO\\AO%number%\\VAL\",\"J2-%symbol%\",\"F\",\"D\",\"H\",\"F\",\"F\",\"F\",\"F\",0,100,0,1,0,0,\"%\",,,,,,\"J2\",\"J2!aq[%number%].q_value\",\"A\",,,,,," + System.lineSeparator();

            String derivedTagsTemplate = "\"J2\\AO\\AO%number%\\SIM\",\"J2\\AO\\AO%number%\\State & 1 == 1\",\"Симулировать параметр\",\"KS\"" + System.lineSeparator();
            try {
                List<IoUnit> ioUnits = (List<IoUnit>) new IoUnitSimpleValidator().validate(srcIoUnits);

                File file = new File("ao.csv");
                File derivedFile = new File("derivedAo.csv");

                file.createNewFile();
                derivedFile.createNewFile();

                /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                BufferedWriter derivedBufferedWriter = new BufferedWriter(new FileWriter(derivedFile.getAbsoluteFile()));*/

                Writer bufferedWriter = new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "windows-1251");
                Writer derivedBufferedWriter = new OutputStreamWriter(new FileOutputStream(derivedFile.getAbsoluteFile()), "windows-1251");

                bufferedWriter.write(HEADER);
                derivedBufferedWriter.write(HEADER);

                ioUnits.stream().map(ioUnit -> tagsTemplate
                        .replace("%number%", String.valueOf(ioUnit.getNumber()))
                        .replace("%description%", ioUnit.getDescription())
                        .replace("%symbol%", ioUnit.getSymbol())
                        ).forEach(s -> {
                    try {
                        bufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                bufferedWriter.close();

                ioUnits.stream().map(ioUnit -> derivedTagsTemplate
                        .replace("%number%", String.valueOf(ioUnit.getNumber()))
                        .replace("%description%", ioUnit.getDescription())
                        .replace("%symbol%", ioUnit.getSymbol())).
                        forEach(s -> {
                    try {

                       derivedBufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                derivedBufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(calculation).start();
    }

    private static void generateDoTags(String json) {

        ArrayList<IoUnit> srcIoUnits = new ArrayList<>();

        Gson gson = new GsonBuilder().registerTypeAdapter(IoUnit.class, new NullIoUnitTypeAdapter()).create();

        Runnable calculation = () -> {
            new JsonParser()
                    .parse(json)
                    .getAsJsonObject()
                    .get("digitalOutputs")
                    .getAsJsonArray()
                    .forEach(jsonElement -> {
                        IoUnit unit = gson.fromJson(jsonElement, IoUnit.class);
                        srcIoUnits.add(unit);
                    });

            String tagsTemplate = "\"A\",\"BLR3\\DO\\DO%number%\\Data\",\"%description%\",\"F\",\"D\",\"*\",\"F\",\"F\",\"U\",\"L\",0,65538,0,1,0,0,\"\",,,,,,\"J2\",\"J2!dq[%number%].state\",\"A\",,,,,," + System.lineSeparator() +
                    "\"D\",\"BLR3\\DO\\DO%number%\\FRC\",\"%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Симулировать\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"BLR3\\DO\\DO%number%\\out\",\"%number%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"Вых.алгоритма\",\"Off\",,,,,,,,,,," + System.lineSeparator() +
                    "\"D\",\"BLR3\\DO\\DO%number%\\val\",\"J2-%symbol%\",\"F\",\"M\",\"*\",\"F\",\"F\",,,,,,,,,,\"Off\",\"=1\",\"Off\",,,,,,,,,,," + System.lineSeparator();

            String derivedTagsTemplate = "\"J2\\DO\\DO%number%\\val\",\"J2\\DO\\DO%number%\\Data & 2\",\"\",\"DOS\"" + System.lineSeparator() +
                    "\"J2\\DO\\DO%number%\\FRC\",\"J2\\DO\\DO%number%\\Data & 4\",\"Need SIM\",\"DOS\"" + System.lineSeparator() +
                    "\"J2\\DO\\DO%number%\\out\",\"J2\\DO\\DO%number%\\Data & 1\",\"\",\"DOS\"" + System.lineSeparator();
            try {
                List<IoUnit> ioUnits = (List<IoUnit>) new IoUnitSimpleValidator().validate(srcIoUnits);

                File file = new File("do.csv");
                File derivedFile = new File("derivedDo.csv");

                file.createNewFile();
                derivedFile.createNewFile();

                /*BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getAbsoluteFile()));
                BufferedWriter derivedBufferedWriter = new BufferedWriter(new FileWriter(derivedFile.getAbsoluteFile()));*/

                Writer bufferedWriter = new OutputStreamWriter(new FileOutputStream(file.getAbsoluteFile()), "windows-1251");
                Writer derivedBufferedWriter = new OutputStreamWriter(new FileOutputStream(derivedFile.getAbsoluteFile()), "windows-1251");

                bufferedWriter.write(HEADER);
                derivedBufferedWriter.write(HEADER);

                ioUnits.stream().map(ioUnit -> tagsTemplate
                        .replace("%number%", String.valueOf(ioUnit.getNumber()))
                        .replace("%description%", ioUnit.getDescription())
                        .replace("%symbol%", ioUnit.getSymbol())
                        ).forEach(s -> {
                    try {
                        bufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                bufferedWriter.close();

                ioUnits.stream().map(ioUnit -> derivedTagsTemplate
                        .replace("%number%", String.valueOf(ioUnit.getNumber()))
                        .replace("%description%", ioUnit.getDescription())
                        .replace("%symbol%", ioUnit.getSymbol())).
                        forEach(s -> {
                    try {
                       derivedBufferedWriter.write(s);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                derivedBufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        new Thread(calculation).start();
    }
}
