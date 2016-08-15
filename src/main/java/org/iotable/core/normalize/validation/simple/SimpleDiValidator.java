package org.iotable.core.normalize.validation.simple;


import org.iotable.core.Config;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.normalize.validation.DiValidator;
import org.iotable.core.normalize.validation.BaseIoUnitValidator;

import java.util.ArrayList;
import java.util.List;

public final class SimpleDiValidator extends BaseIoUnitValidator implements DiValidator {

    @Override
    public List<DiscreteInput> validate(List<DiscreteInput> discreteInputs) {

        List<DiscreteInput> validDis = new ArrayList<>();

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
