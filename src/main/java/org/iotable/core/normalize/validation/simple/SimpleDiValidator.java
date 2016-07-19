package org.iotable.core.normalize.validation.simple;


import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.normalize.validation.DiValidator;
import org.iotable.core.normalize.validation.BaseIoUnitValidator;

import java.util.ArrayList;
import java.util.List;

public final class SimpleDiValidator implements DiValidator {

    private static final BaseIoUnitValidator baseValidator = new BaseIoUnitValidator();

    @Override
    public List<DiscreteInput> validate(List<DiscreteInput> discreteInputs) {

        List<DiscreteInput> validDis = new ArrayList<>();

        for (DiscreteInput di : discreteInputs) {
            DiscreteInput validDi = new DiscreteInput(baseValidator.validate(di.getIoUnit()));
            validDis.add(validDi);
        }

        return validDis;
    }
}
