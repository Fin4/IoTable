package org.rldev.service;


import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.normalize.equality.IoUnitEqualityChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EqualityServiceImpl implements EqualityService {

    @Autowired private IoUnitEqualityChecker equalityChecker;

    @Override
    public List<IoUnit> findEqualsByNumber(List<IoUnit> ioUnits) {
        return equalityChecker.findEqualsByNumber(ioUnits);
    }

    @Override
    public List<IoUnit> findEqualsByAddress(List<IoUnit> ioUnits) {
        return equalityChecker.findEqualsByAddress(ioUnits);
    }

    @Override
    public List<IoUnit> findEqualsBySymbol(List<IoUnit> ioUnits) {
        return equalityChecker.findEqualsBySymbol(ioUnits);
    }

    @Override
    public List<IoUnit> findDuplicates(List<IoUnit> ioUnits) {
        return equalityChecker.findDuplicates(ioUnits);
    }
}
