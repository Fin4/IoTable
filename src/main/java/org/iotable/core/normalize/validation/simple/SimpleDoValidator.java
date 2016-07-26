package org.iotable.core.normalize.validation.simple;

import org.iotable.core.model.ioUnits.DiscreteOutput;
import org.iotable.core.model.ioUnits.IoUnit;
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

            IoUnit ioUnit = baseValidator.validate(discreteOutput.getIoUnit());
            String symbol;
            if (ioUnit.symbol.equals("res")) symbol = "doRes" + ioUnit.number;
            else symbol = ioUnit.symbol;

            DiscreteOutput validDo = new DiscreteOutput(new IoUnit(symbol, ioUnit.description, ioUnit.address, ioUnit.number));
            validDos.add(validDo);
        }

        return validDos;
    }
}
