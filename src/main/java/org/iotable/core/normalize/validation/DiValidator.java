package org.iotable.core.normalize.validation;

import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.List;

public interface DiValidator {

    List<DiscreteInput> validate(List<DiscreteInput> discreteInputs);
}
