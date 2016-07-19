package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.List;

public interface AiValidator {
    List<AnalogInput> validate(List<AnalogInput> analogInputs);
}
