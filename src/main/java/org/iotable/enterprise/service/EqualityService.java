package org.iotable.enterprise.service;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.IoUnit;

import java.util.List;

public interface EqualityService {

    List<IoUnit> findDuplicateAis(IoTable ioTable);

    List<IoUnit> findDuplicateDis(IoTable ioTable);

    List<IoUnit> findDuplicateAos(IoTable ioTable);

    List<IoUnit> findDuplicateDos(IoTable ioTable);

}
