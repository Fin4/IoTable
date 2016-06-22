package org.iotable.enterprise.service;


import org.iotable.core.model.ioUnits.IoUnit;

import java.util.List;

public interface EqualityService {

    List<IoUnit> findEqualsByNumber(List<IoUnit> ioUnits);

    List<IoUnit> findEqualsByAddress(List<IoUnit> ioUnits);

    List<IoUnit> findEqualsBySymbol(List<IoUnit> ioUnits);

    List<IoUnit> findDuplicates(List<IoUnit> ioUnits);

}
