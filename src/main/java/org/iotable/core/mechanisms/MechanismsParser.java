package org.iotable.core.mechanisms;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.model.mechanisms.Mechanism;

import java.util.List;

public interface MechanismsParser {

    List<Mechanism> getBySymbol(IoTable ioTable);

    List<Mechanism> getByDescription(IoTable ioTable);

    List<Mechanism> getEntire(IoTable ioTable);

}
