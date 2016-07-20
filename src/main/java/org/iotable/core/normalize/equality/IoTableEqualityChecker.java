package org.iotable.core.normalize.equality;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.IoUnit;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface IoTableEqualityChecker {

    Map<String, List> findDuplicates(IoTable ioTable);

    default List<IoUnit> duplicatesBy(List<IoUnit> ioUnits, Function<IoUnit, Object> function) {
        List<IoUnit> d = new ArrayList<>();
        ioUnits.stream()
                .collect(Collectors.groupingBy(function))
                .entrySet().stream().filter(entry -> entry.getValue().size() > 1)
                .map(Map.Entry::getValue)
                .forEach(d::addAll);

        return d;
    }

    default List<IoUnit> duplicatesByNumber(List<IoUnit> ioUnits) {
        return duplicatesBy(ioUnits, (ioUnit -> ioUnit.number));
    }

    default List<IoUnit> duplicatesByAddress(List<IoUnit> ioUnits) {
        return duplicatesBy(ioUnits, (ioUnit -> ioUnit.address));
    }

    default List<IoUnit> duplicatesBySymbol(List<IoUnit> ioUnits) {
        return duplicatesBy(ioUnits, (ioUnit -> ioUnit.symbol));
    }

    default List<IoUnit> duplicates(List<IoUnit> ioUnits) {
        return Stream.of(duplicatesByNumber(ioUnits), duplicatesByAddress(ioUnits), duplicatesBySymbol(ioUnits))
                .distinct()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }
}
