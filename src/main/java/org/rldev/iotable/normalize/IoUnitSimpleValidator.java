package org.rldev.iotable.normalize;

import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;

public class IoUnitSimpleValidator implements IoUnitValidator {

    private IoUnitValidator validator;

    public IoUnitSimpleValidator(IoUnitValidator validator) {
        this.validator = validator;
    }

    public IoUnitSimpleValidator() {}

    @Override
    public List<? extends IoUnit> validate(List<? extends IoUnit> ioUnits) {

        for (IoUnit ioUnit : ioUnits) {

            ioUnit.setAddress(ioUnit.getAddress().trim());

            String desc = ioUnit.getDescription().replace(String.valueOf((char) 160), " ").trim();

            ioUnit.setDescription(desc.replaceAll("«|»", "\""));

            if ((ioUnit.getSymbol() == null) || ioUnit.getSymbol().isEmpty()) {
                ioUnit.setSymbol(ioUnit.getClass().getSimpleName() + ".res" + ioUnit.getNumber());
            } else ioUnit.setSymbol(ioUnit.getSymbol().trim());

        }

        if (validator != null) validator.validate(ioUnits);

        return ioUnits;
    }
}
