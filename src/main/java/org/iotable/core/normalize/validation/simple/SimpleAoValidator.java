package org.iotable.core.normalize.validation.simple;


import org.iotable.core.Config;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.normalize.validation.AoValidator;
import org.iotable.core.normalize.validation.BaseValidator;

import java.util.ArrayList;
import java.util.List;

public final class SimpleAoValidator extends BaseValidator implements AoValidator {

    @Override
    public List<AnalogOutput> validate(List<AnalogOutput> analogOutputs) {

        List<AnalogOutput> validAos = new ArrayList<>();

        for (AnalogOutput ao : analogOutputs) {

            IoUnit ioUnit = validate(ao.getIoUnit());

            String symbol;
            if (ioUnit.symbol.equals("res")) symbol = Config.getProperty("ao.res") + ioUnit.number;
            else symbol = ioUnit.symbol;

            AnalogOutput validAo = new AnalogOutput(new IoUnit(symbol, ioUnit.description, ioUnit.address, ioUnit.number));
            validAos.add(validAo);
        }

        return validAos;
    }
}
