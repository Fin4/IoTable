package org.iotable.core.mechanisms;


import org.iotable.core.model.IoTable;
import org.iotable.core.model.mechanisms.Mechanism;

import java.util.List;
import java.util.Map;


public interface MechanismsParser {

    List<Mechanism> getBySymbol(IoTable ioTable);

    List<Mechanism> getByDescription(IoTable ioTable);

    List<Mechanism> getEntire(IoTable ioTable);

    List<Mechanism> getByType(final String type, final List<Mechanism> mechanisms);
}
