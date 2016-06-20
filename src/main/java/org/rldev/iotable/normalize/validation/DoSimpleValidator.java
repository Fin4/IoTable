package org.rldev.iotable.normalize.validation;

import org.rldev.iotable.model.ioUnits.DiscreteOutput;

import java.util.List;

public class DoSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<DiscreteOutput> {

    @Override
    public List<DiscreteOutput> validate(List<DiscreteOutput> ioUnits) {

        super.baseValidate(ioUnits);
        return ioUnits;
    }
}
