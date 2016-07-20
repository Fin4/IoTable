package org.iotable.enterprise.service;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.normalize.equality.IoTableEqualityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EqualityServiceImpl implements EqualityService {

    @Autowired private IoTableEqualityChecker equalityChecker;

    @Override
    public List<IoUnit> findDuplicateAis(IoTable ioTable) {
        return equalityChecker.duplicates(ioTable.getAiIoUnits());
    }

    @Override
    public List<IoUnit> findDuplicateDis(IoTable ioTable) {
        return equalityChecker.duplicates(ioTable.getDiIoUnits());
    }

    @Override
    public List<IoUnit> findDuplicateAos(IoTable ioTable) {
        return equalityChecker.duplicates(ioTable.getAoIoUnits());
    }

    @Override
    public List<IoUnit> findDuplicateDos(IoTable ioTable) {
        return equalityChecker.duplicates(ioTable.getDoIoUnits());
    }
}
