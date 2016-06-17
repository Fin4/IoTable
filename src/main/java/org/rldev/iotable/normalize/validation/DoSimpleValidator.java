package org.rldev.iotable.normalize.validation;

import org.rldev.iotable.model.ioUnits.DigitalOutput;

import java.util.List;

public class DoSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<DigitalOutput> {

    @Override
    public List<DigitalOutput> validate(List<DigitalOutput> ioUnits) {

        super.baseValidate(ioUnits);
        return ioUnits;
    }
}
