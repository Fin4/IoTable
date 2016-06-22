package org.iotable.core.mechanisms;


import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.model.mechanisms.Mechanism;

import java.util.List;

public interface MechanismsParser {

    List<Mechanism> getBySymbol(List<IoUnit> ioUnits);

    List<Mechanism> getByDescription(List<IoUnit> ioUnits);

    List<Mechanism> getEntire(List<IoUnit> ioUnits);

}
