package org.iotable.core.normalize.equality;


import org.iotable.core.model.ioUnits.IoUnit;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleIoUnitEqualityChecker implements IoUnitEqualityChecker {

    @Override
    public List<IoUnit> findEqualsByNumber(List<IoUnit> ioUnits) {

        Map<Integer, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(IoUnit::getNumber));

        List<IoUnit> duplicates = new ArrayList<>();

        map.entrySet().stream().filter(entry -> entry.getValue().size() > 1).forEach(entry -> duplicates.addAll(entry.getValue()));

        return duplicates;
    }

    @Override
    public List<IoUnit> findEqualsByAddress(List<IoUnit> ioUnits) {

        Map<String, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(IoUnit::getAddress));

        List<IoUnit> duplicates = new ArrayList<>();

        map.entrySet().stream().filter(entry -> entry.getValue().size() > 1).forEach(entry -> duplicates.addAll(entry.getValue()));

        return duplicates;
    }

    @Override
    public List<IoUnit> findEqualsBySymbol(List<IoUnit> ioUnits) {

        Map<String, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(IoUnit::getSymbol));

        List<IoUnit> duplicates = new ArrayList<>();

        map.entrySet().stream().filter(entry -> entry.getValue().size() > 1).forEach(entry -> duplicates.addAll(entry.getValue()));

        return duplicates;
    }

    @Override
    public List<IoUnit> findDuplicates(List<IoUnit> ioUnits) {

        List<IoUnit> equalsByNumber = findEqualsByNumber(ioUnits);
        List<IoUnit> equalsByAddress = findEqualsByAddress(ioUnits);
        List<IoUnit> equalsBySymbol = findEqualsBySymbol(ioUnits);

        List<IoUnit> ioUnitList = new ArrayList<>(equalsByNumber);
        ioUnitList.addAll(equalsByAddress);
        ioUnitList.addAll(equalsBySymbol);

        return ioUnitList.stream().distinct().collect(Collectors.toList());
    }
}
