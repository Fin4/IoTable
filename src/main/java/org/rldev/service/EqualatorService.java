package org.rldev.service;


import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;

public interface EqualatorService {

    List<IoUnit> findEquals(List<IoUnit> ioUnits);

}
