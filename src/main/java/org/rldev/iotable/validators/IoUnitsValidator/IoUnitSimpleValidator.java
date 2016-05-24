package org.rldev.iotable.validators.IoUnitsValidator;

import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.ArrayList;
import java.util.List;

public class IoUnitSimpleValidator implements IoUnitValidator {

    @Override
    public List<? extends IoUnit> validate(List<? extends IoUnit> ioUnits) {

        List<IoUnit> validIoUnits = new ArrayList<>();

        for (IoUnit ioUnit : ioUnits) {

            IoUnit validIoUnit = new IoUnit();

            validIoUnit.setNumber(ioUnit.getNumber());

            validIoUnit.setAddress(ioUnit.getAddress().replace("/", ".").trim());

            validIoUnit.setDescription(ioUnit.getDescription());

            if (ioUnit.getSymbol().trim().equals("") || (ioUnit.getSymbol() == null)) {
                validIoUnit.setSymbol("ioUnit[" + ioUnit.getNumber() + "]");
            } else if (ioUnit.getSymbol().contains("-")) {

                StringBuilder symbol = new StringBuilder(ioUnit.getSymbol().trim());

                symbol.delete(0, ioUnit.getSymbol().indexOf("-") + 1);

                validIoUnit.setSymbol(symbol.toString());

            } else validIoUnit.setSymbol(ioUnit.getSymbol());

            validIoUnits.add(validIoUnit);

        }
        return validIoUnits;
    }
}
