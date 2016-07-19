package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.List;

public interface AoValidator {

    List<AnalogOutput> validate(List<AnalogOutput> analogOutputs);
}
