package org.iotable.core.normalize.validation;


import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.List;

public class AoSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<AnalogOutput> {

    @Override
    public List<AnalogOutput> validate(List<AnalogOutput> ioUnits) {
        super.baseValidate(ioUnits);
        return ioUnits;
    }
}
