package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.List;

public interface DoValidator {

    List<DiscreteOutput> validate(List<DiscreteOutput> discreteOutputs);
}
