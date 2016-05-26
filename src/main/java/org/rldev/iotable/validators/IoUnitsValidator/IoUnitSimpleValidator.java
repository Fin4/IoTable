package org.rldev.iotable.validators.IoUnitsValidator;

import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.ArrayList;
import java.util.List;

public class IoUnitSimpleValidator implements IoUnitValidator {

    @Override
    public List<? extends IoUnit> validate(List<? extends IoUnit> ioUnits) {

        for (IoUnit ioUnit : ioUnits) {

            ioUnit.setNumber(ioUnit.getNumber());

            ioUnit.setAddress(ioUnit.getAddress().replace("/", ".").trim());

            ioUnit.setDescription(ioUnit.getDescription().replaceAll("«|»", "\"").trim());

            if ((ioUnit.getSymbol() == null) || ioUnit.getSymbol().trim().equals("")) {
                ioUnit.setSymbol(ioUnit.getClass().getSimpleName() + ".reserve" + ioUnit.getNumber());
            } else if (ioUnit.getSymbol().contains("-")) {

                StringBuilder symbol = new StringBuilder(ioUnit.getSymbol().trim());

                symbol.delete(0, ioUnit.getSymbol().indexOf("-") + 1);

                ioUnit.setSymbol(symbol.toString());

            } else ioUnit.setSymbol(ioUnit.getSymbol());
        }
        return ioUnits;
    }
}
