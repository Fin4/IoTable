package org.rldev.service;


import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.normalize.equalation.IoUnitEqualatorImpl;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("equalatorService")
public class EqualatorServiceImpl implements EqualatorService {

    @Override
    public List<IoUnit> findEquals(List<IoUnit> ioUnits) {
        return new IoUnitEqualatorImpl().findEqualsByNumber(ioUnits);
    }
}
