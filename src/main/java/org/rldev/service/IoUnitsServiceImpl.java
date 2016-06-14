package org.rldev.service;

import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.normalize.equalation.IoUnitEqualatorImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IoUnitsServiceImpl implements IoUnitsService {

    @Override
    public List<IoUnit> findEqualsByNumber(List<IoUnit> ioUnits) {
        return new IoUnitEqualatorImpl().findEqualsByNumber(ioUnits);
    }

    @Override
    public List<IoUnit> findEqualsByAddress(List<IoUnit> ioUnits) {
        return new IoUnitEqualatorImpl().findEqualsByAddress(ioUnits);
    }

    @Override
    public List<IoUnit> findEqualsBySymbol(List<IoUnit> ioUnits) {
        return new IoUnitEqualatorImpl().findEqualsBySymbol(ioUnits);
    }
}
