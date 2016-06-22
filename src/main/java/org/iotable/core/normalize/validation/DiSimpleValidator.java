package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.List;

public class DiSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<DiscreteInput> {

    @Override
    public List<DiscreteInput> validate(List<DiscreteInput> ioUnits) {
        super.baseValidate(ioUnits);
        return ioUnits;
    }
}
