package org.iotable.core.normalize.validation.simple;


import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.normalize.validation.AoValidator;
import org.iotable.core.normalize.validation.BaseIoUnitValidator;

import java.util.ArrayList;
import java.util.List;

public final class SimpleAoValidator implements AoValidator {

    private static final BaseIoUnitValidator baseValidator = new BaseIoUnitValidator();

    @Override
    public List<AnalogOutput> validate(List<AnalogOutput> analogOutputs) {

        List<AnalogOutput> validAos = new ArrayList<>();

        for (AnalogOutput ao : analogOutputs) {
            AnalogOutput validAo = new AnalogOutput(baseValidator.validate(ao.getIoUnit()));
            validAos.add(validAo);
        }

        return validAos;
    }
}
