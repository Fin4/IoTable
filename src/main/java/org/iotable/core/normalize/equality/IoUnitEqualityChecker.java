package org.iotable.core.normalize.equality;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.IoUnit;

import java.util.List;
import java.util.Map;

public interface IoUnitEqualityChecker {

    Map<String, List> findEqualsByNumber(IoTable ioTable);

    Map<String, List> findEqualsByAddress(IoTable ioTable);

    Map<String, List> findEqualsBySymbol(IoTable ioTable);

    Map<String, List> findDuplicates(IoTable ioTable);
}
