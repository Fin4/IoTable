package org.iotable.core.normalize.equality;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.IoUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public interface IoTableEqualityChecker {

    Map<String, List> findDuplicates(IoTable ioTable);

    default List<IoUnit> duplicatesByNumber(List<IoUnit> ioUnits) {

        return ioUnits.stream()
                .collect(Collectors.groupingBy(ioUnit -> ioUnit.number))
                .entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getValue)
                .flatMap(ioUnits1 -> ioUnits.stream()).collect(Collectors.toList());
    }

    default List<IoUnit> duplicatesByAddress(List<IoUnit> ioUnits) {

        return ioUnits.stream()
                .collect(Collectors.groupingBy(ioUnit -> ioUnit.address))
                .entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getValue)
                .flatMap(ioUnits1 -> ioUnits.stream()).collect(Collectors.toList());
    }

    default List<IoUnit> duplicatesBySymbol(List<IoUnit> ioUnits) {

        return ioUnits.stream()
                .collect(Collectors.groupingBy(ioUnit -> ioUnit.symbol))
                .entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getValue)
                .flatMap(ioUnits1 -> ioUnits.stream()).collect(Collectors.toList());
    }

    default List<IoUnit> duplicates(List<IoUnit> ioUnits) {

        List<IoUnit> duplicatesByNumber = duplicatesByNumber(ioUnits);
        List<IoUnit> duplicatesByAddress = duplicatesByAddress(ioUnits);
        List<IoUnit> duplicatesBySymbol = duplicatesBySymbol(ioUnits);

        List<IoUnit> duplicates = new ArrayList<>(duplicatesByNumber);
        duplicates.addAll(duplicatesByAddress);
        duplicates.addAll(duplicatesBySymbol);

        return duplicates.stream().distinct().collect(Collectors.toList());
    }
}
