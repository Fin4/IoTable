package org.rldev.iotable.validators.IoUnitsValidator;

import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.*;

public class AiSimpleValidator implements AiValidator {

    @Override
    public List<AnalogInput> validate(List<? extends IoUnit> ioUnits) {

        List<AnalogInput> validIoUnits = new ArrayList<>();

        for (IoUnit ioUnit : ioUnits) {

            AnalogInput validIoUnit = new AnalogInput();

            validIoUnit.setNumber(ioUnit.getNumber());

            validIoUnit.setAddress(ioUnit.getAddress().replace("/", ".").trim());

            validIoUnit.setDescription(ioUnit.getDescription().trim());

            String engUnits = ((AnalogInput) ioUnit).getEngUnits();

            if (engUnits.trim().equals("") || engUnits == null) validIoUnit.setEngUnits("");
            else {
                ArrayList<String> tokens = new ArrayList<>(Arrays.asList(engUnits.split(" ")));
                validIoUnit.setEngUnits(tokens.get(tokens.size() - 1));
            }

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
