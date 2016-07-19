package org.iotable.core.normalize.equality;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.*;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleIoUnitEqualityChecker implements IoUnitEqualityChecker {

    @Override
    public Map<String, List> findEqualsByNumber(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DiscreteInput> discreteInputs = ioTable.getDiscreteInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DiscreteOutput> discreteOutputs = ioTable.getDiscreteOutputs();

        Map<String, List> duplicates = new HashMap<>();

        List<AnalogInput> duplicateAis = new ArrayList<>();
        Map<Integer, List<AnalogInput>> mapAi = analogInputs.stream()
                .collect(Collectors.groupingBy(analogInput -> analogInput.getIoUnit().number));
        mapAi.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateAis.addAll(entry.getValue()));

        List<DiscreteInput> duplicateDis = new ArrayList<>();
        Map<Integer, List<DiscreteInput>> mapDi = discreteInputs.stream()
                .collect(Collectors.groupingBy(discreteInput -> discreteInput.getIoUnit().number));
        mapDi.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateDis.addAll(entry.getValue()));

        List<AnalogOutput> duplicateAos = new ArrayList<>();
        Map<Integer, List<AnalogOutput>> mapAo = analogOutputs.stream()
                .collect(Collectors.groupingBy(analogOutput -> analogOutput.getIoUnit().number));
        mapAo.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateAos.addAll(entry.getValue()));

        List<DiscreteOutput> duplicateDos = new ArrayList<>();
        Map<Integer, List<DiscreteOutput>> mapDo = discreteOutputs.stream()
                .collect(Collectors.groupingBy(discreteOutput -> discreteOutput.getIoUnit().number));
        mapDo.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateDos.addAll(entry.getValue()));


        duplicates.put("AI", duplicateAis);
        duplicates.put("DI", duplicateDis);
        duplicates.put("AO", duplicateAos);
        duplicates.put("DI", duplicateDos);

        return duplicates;
    }

    @Override
    public Map<String, List> findEqualsByAddress(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DiscreteInput> discreteInputs = ioTable.getDiscreteInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DiscreteOutput> discreteOutputs = ioTable.getDiscreteOutputs();

        Map<String, List> duplicates = new HashMap<>();

        List<AnalogInput> duplicateAis = new ArrayList<>();
        Map<String, List<AnalogInput>> mapAi = analogInputs.stream()
                .collect(Collectors.groupingBy(analogInput -> analogInput.getIoUnit().address));
        mapAi.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateAis.addAll(entry.getValue()));

        List<DiscreteInput> duplicateDis = new ArrayList<>();
        Map<String, List<DiscreteInput>> mapDi = discreteInputs.stream()
                .collect(Collectors.groupingBy(discreteInput -> discreteInput.getIoUnit().address));
        mapDi.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateDis.addAll(entry.getValue()));

        List<AnalogOutput> duplicateAos = new ArrayList<>();
        Map<String, List<AnalogOutput>> mapAo = analogOutputs.stream()
                .collect(Collectors.groupingBy(analogOutput -> analogOutput.getIoUnit().address));
        mapAo.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateAos.addAll(entry.getValue()));

        List<DiscreteOutput> duplicateDos = new ArrayList<>();
        Map<String, List<DiscreteOutput>> mapDo = discreteOutputs.stream()
                .collect(Collectors.groupingBy(discreteOutput -> discreteOutput.getIoUnit().address));
        mapDo.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateDos.addAll(entry.getValue()));


        duplicates.put("AI", duplicateAis);
        duplicates.put("DI", duplicateDis);
        duplicates.put("AO", duplicateAos);
        duplicates.put("DI", duplicateDos);

        return duplicates;
    }

    @Override
    public Map<String, List> findEqualsBySymbol(IoTable ioTable) {

        List<AnalogInput> analogInputs = ioTable.getAnalogInputs();
        List<DiscreteInput> discreteInputs = ioTable.getDiscreteInputs();
        List<AnalogOutput> analogOutputs = ioTable.getAnalogOutputs();
        List<DiscreteOutput> discreteOutputs = ioTable.getDiscreteOutputs();

        Map<String, List> duplicates = new HashMap<>();

        List<AnalogInput> duplicateAis = new ArrayList<>();
        Map<String, List<AnalogInput>> mapAi = analogInputs.stream()
                .collect(Collectors.groupingBy(analogInput -> analogInput.getIoUnit().symbol));
        mapAi.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateAis.addAll(entry.getValue()));

        List<DiscreteInput> duplicateDis = new ArrayList<>();
        Map<String, List<DiscreteInput>> mapDi = discreteInputs.stream()
                .collect(Collectors.groupingBy(discreteInput -> discreteInput.getIoUnit().symbol));
        mapDi.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateDis.addAll(entry.getValue()));

        List<AnalogOutput> duplicateAos = new ArrayList<>();
        Map<String, List<AnalogOutput>> mapAo = analogOutputs.stream()
                .collect(Collectors.groupingBy(analogOutput -> analogOutput.getIoUnit().symbol));
        mapAo.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateAos.addAll(entry.getValue()));

        List<DiscreteOutput> duplicateDos = new ArrayList<>();
        Map<String, List<DiscreteOutput>> mapDo = discreteOutputs.stream()
                .collect(Collectors.groupingBy(discreteOutput -> discreteOutput.getIoUnit().symbol));
        mapDo.entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .forEach(entry -> duplicateDos.addAll(entry.getValue()));


        duplicates.put("AI", duplicateAis);
        duplicates.put("DI", duplicateDis);
        duplicates.put("AO", duplicateAos);
        duplicates.put("DI", duplicateDos);

        return duplicates;
    }

    @Override
    public Map<String, List> findDuplicates(IoTable ioTable) {

        Map<String, List> duplicates = new HashMap<>();

        List<AnalogInput> equalsByNumber = findEqualsByNumber(ioTable).get("AI");
        List<AnalogInput> equalsByAddress = findEqualsByAddress(ioTable).get("AI");
        List<AnalogInput> equalsBySymbol = findEqualsBySymbol(ioTable).get("AI");

        List<AnalogInput> aiList = new ArrayList<>();
        aiList.addAll(equalsByNumber);
        aiList.addAll(equalsByAddress);
        aiList.addAll(equalsBySymbol);

        duplicates.put("AI", aiList.stream().distinct().collect(Collectors.toList()));

        return duplicates;
    }
}
