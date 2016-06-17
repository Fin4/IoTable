package org.rldev.iotable.normalize.validation;


import org.rldev.iotable.model.ioUnits.AnalogOutput;

import java.util.List;

public class AoSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<AnalogOutput> {

    @Override
    public List<AnalogOutput> validate(List<AnalogOutput> ioUnits) {
        super.baseValidate(ioUnits);
        return ioUnits;
    }
}
