package org.rldev.iotable.validators.IoUnitsValidator;

import org.rldev.iotable.model.IoUnit;

import java.util.*;

public class AiSimpleValidator implements AiValidator {

    @Override
    public List<? extends IoUnit> validate(List<? extends IoUnit> ioUnits) {

        List<IoUnit> validIoUnits = new ArrayList<>();

        for (IoUnit ioUnit : ioUnits) {

            IoUnit validIoUnit = new IoUnit();

            validIoUnit.setNumber(ioUnit.getNumber());

            validIoUnit.setAddress(ioUnit.getAddress().replace("/", ".").trim());

            validIoUnit.setDescription(ioUnit.getDescription().trim());

            if (ioUnit.getSymbol().trim().equals("") || ioUnit.getSymbol() == null) {
                validIoUnit.setSymbol("ai[" + ioUnit.getNumber() + "]");
            } else if (ioUnit.getSymbol().contains("-")) {

                StringBuilder symbol = new StringBuilder(ioUnit.getSymbol().trim());

                symbol.delete(0, ioUnit.getSymbol().indexOf("-") + 1);

                validIoUnit.setSymbol(symbol.toString().replace(".", "_"));

            } else validIoUnit.setSymbol(ioUnit.getSymbol().replace(".", "_"));

            validIoUnits.add(validIoUnit);
        }
        return validIoUnits;
    }
}
