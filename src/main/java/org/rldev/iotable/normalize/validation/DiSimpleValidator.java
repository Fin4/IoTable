package org.rldev.iotable.normalize.validation;


import org.rldev.iotable.model.ioUnits.DigitalInput;

import java.util.List;

public class DiSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<DigitalInput> {

    @Override
    public List<DigitalInput> validate(List<DigitalInput> ioUnits) {
        super.baseValidate(ioUnits);
        return ioUnits;
    }
}
