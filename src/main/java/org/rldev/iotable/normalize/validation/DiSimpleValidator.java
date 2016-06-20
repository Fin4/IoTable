package org.rldev.iotable.normalize.validation;


import org.rldev.iotable.model.ioUnits.DiscreteInput;

import java.util.List;

public class DiSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<DiscreteInput> {

    @Override
    public List<DiscreteInput> validate(List<DiscreteInput> ioUnits) {
        super.baseValidate(ioUnits);
        return ioUnits;
    }
}
