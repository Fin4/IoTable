package org.iotable.core.normalize.equality;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.*;

import java.util.*;
import java.util.stream.Collectors;

public class SimpleIoTableEqualityChecker implements IoTableEqualityChecker {

    @Override
    public Map<String, List> findDuplicates(IoTable ioTable) {

        Map<String, List> duplicates = new HashMap<>();

        duplicates.put("AI", duplicates(ioTable.getAiIoUnits()));
        duplicates.put("DI", duplicates(ioTable.getDiIoUnits()));
        duplicates.put("AO", duplicates(ioTable.getAoIoUnits()));
        duplicates.put("DO", duplicates(ioTable.getDoIoUnits()));

        return duplicates;
    }
}
