package org.rldev.iotable.validators.IoUnitsValidator;

import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.*;

public class AiSimpleValidator implements AiValidator {

    @Override
    public List<AnalogInput> validate(List<? extends IoUnit> ioUnits) {

        for (IoUnit ioUnit : ioUnits) {

            AnalogInput analogInput = (AnalogInput) ioUnit;

            analogInput.setNumber(ioUnit.getNumber());

            analogInput.setAddress(ioUnit.getAddress().replace("/", ".").trim());

            analogInput.setDescription(ioUnit.getDescription().trim());

            String engUnits = analogInput.getEngUnits();

            if (engUnits == null || engUnits.trim().equals("")) analogInput.setEngUnits("");
            else {
                ArrayList<String> tokens = new ArrayList<>(Arrays.asList(engUnits.split(" ")));
                analogInput.setEngUnits(tokens.get(tokens.size() - 1));
            }

            if (analogInput.getSymbol().trim().equals("") || analogInput.getSymbol() == null) {
                analogInput.setSymbol(analogInput.getClass().getSimpleName() + ".reserve" + ioUnit.getNumber());
            } else if (analogInput.getSymbol().contains("-")) {

                StringBuilder symbol = new StringBuilder(analogInput.getSymbol().trim());

                symbol.delete(0, ioUnit.getSymbol().indexOf("-") + 1);

                analogInput.setSymbol(symbol.toString().replace(".", "_"));

            } else analogInput.setSymbol(ioUnit.getSymbol().replace(".", "_"));


        }
        return (List<AnalogInput>) ioUnits;
    }
}
