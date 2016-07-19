package org.iotable.core.normalize.validation.simple;

import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.normalize.validation.DoValidator;
import org.iotable.core.normalize.validation.BaseIoUnitValidator;

import java.util.ArrayList;
import java.util.List;

public final class SimpleDoValidator implements DoValidator {

    private static final BaseIoUnitValidator baseValidator = new BaseIoUnitValidator();

    @Override
    public List<DiscreteOutput> validate(List<DiscreteOutput> discreteOutputs) {

        List<DiscreteOutput> validDos = new ArrayList<>();

        for (DiscreteOutput discreteOutput : discreteOutputs) {
            DiscreteOutput validDo = new DiscreteOutput(baseValidator.validate(discreteOutput.getIoUnit()));
            validDos.add(validDo);
        }

        return validDos;
    }
}
