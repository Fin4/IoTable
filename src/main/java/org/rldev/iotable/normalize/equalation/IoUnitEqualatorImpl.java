package org.rldev.iotable.normalize.equalation;


import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.*;
import java.util.stream.Collectors;

public class IoUnitEqualatorImpl implements IoUnitEqualator {


    @Override
    public List<IoUnit> findEqualsByNumber(List<IoUnit> ioUnits) {

        Map<Integer, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(ioUnit -> ioUnit.getNumber()));

        List<IoUnit> duplicates = new ArrayList<>();

        map.entrySet().stream().filter(entry -> entry.getValue().size() > 1).forEach(entry -> duplicates.addAll(entry.getValue()));

        return duplicates;
    }

    @Override
    public List<IoUnit> findEqualsByAddress(List<IoUnit> ioUnits) {

        Map<String, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(ioUnit -> ioUnit.getAddress()));

        List<IoUnit> duplicates = new ArrayList<>();

        map.entrySet().stream().filter(entry -> entry.getValue().size() > 1).forEach(entry -> duplicates.addAll(entry.getValue()));

        return duplicates;
    }

    @Override
    public List<IoUnit> findEqualsBySymbol(List<IoUnit> ioUnits) {

        Map<String, List<IoUnit>> map = ioUnits.stream().collect(Collectors.groupingBy(ioUnit -> ioUnit.getSymbol()));

        List<IoUnit> duplicates = new ArrayList<>();

        map.entrySet().stream().filter(entry -> entry.getValue().size() > 1).forEach(entry -> duplicates.addAll(entry.getValue()));

        return duplicates;
    }
}
