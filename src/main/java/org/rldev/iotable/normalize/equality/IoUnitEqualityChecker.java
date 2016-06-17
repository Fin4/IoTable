package org.rldev.iotable.normalize.equality;


import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;

public interface IoUnitEqualityChecker {

    List<IoUnit> findEqualsByNumber(List<IoUnit> ioUnits);

    List<IoUnit> findEqualsByAddress(List<IoUnit> ioUnits);

    List<IoUnit> findEqualsBySymbol(List<IoUnit> ioUnits);
}
