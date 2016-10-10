package org.iotable.core.normalize.validation.simple;


import org.iotable.core.Config;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.normalize.validation.BaseValidator;
import org.iotable.core.normalize.validation.DiValidator;

import java.util.ArrayList;
import java.util.List;

public final class SimpleDiValidator extends BaseValidator implements DiValidator {

    @Override
    public List<DiscreteInput> validate(List<DiscreteInput> discreteInputs) {

        List<DiscreteInput> validDis = new ArrayList<>(discreteInputs.size());

        for (DiscreteInput di : discreteInputs) {

            IoUnit ioUnit = validate(di.getIoUnit());
            String symbol;
            if (ioUnit.symbol.equals("res")) symbol = Config.getProperty("di.res") + ioUnit.number;
            else symbol = ioUnit.symbol;

            DiscreteInput validDi = new DiscreteInput(new IoUnit(symbol, ioUnit.description, ioUnit.address, ioUnit.number));
            validDis.add(validDi);
        }

        return validDis;
    }
}
