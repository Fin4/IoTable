package org.rldev.iotable.normalize.validation;


import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;

abstract class AbstractIoUnitValidator {

    List<? extends IoUnit> baseValidate(List<? extends IoUnit> ioUnits) {

        for (IoUnit ioUnit : ioUnits) {

            ioUnit.setAddress(ioUnit.getAddress().trim());

            String desc = ioUnit.getDescription().replace(String.valueOf((char) 160), " ").trim();

            ioUnit.setDescription(desc.replaceAll("«|»", "\""));

            if ((ioUnit.getSymbol() == null) || ioUnit.getSymbol().isEmpty()) {
                ioUnit.setSymbol(ioUnit.getClass().getSimpleName() + ".res" + ioUnit.getNumber());
            } else ioUnit.setSymbol(ioUnit.getSymbol().trim());

        }

        return ioUnits;
    }
}
