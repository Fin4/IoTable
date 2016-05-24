package org.rldev.iotable.mechanisms;


import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.model.mechanisms.Mechanism;

import java.util.List;

public interface MechanismsParser {

    List<Mechanism> getMechanisms(List<IoUnit> ioUnits);

    List<Mechanism> getMechanisms(String json);
}
