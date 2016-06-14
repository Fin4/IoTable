package org.rldev.iotable.normalize;

import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.*;

public class AiSimpleValidator implements AiValidator {

    @Override
    public List<AnalogInput> validate(List<? extends IoUnit> ioUnits) {

        //List<AnalogInput> analogInputs = new ArrayList<>();

        for (IoUnit ioUnit : ioUnits) {

            AnalogInput analogInput = (AnalogInput) ioUnit;

            String engUnits = analogInput.getEngUnits();

            if (engUnits == null || engUnits.trim().isEmpty()) analogInput.setEngUnits("");
            else {
                ArrayList<String> tokens = new ArrayList<>(Arrays.asList(engUnits.split(" ")));
                analogInput.setEngUnits(tokens.get(tokens.size() - 1).trim());
            }
        }

        return (List<AnalogInput>) ioUnits;
    }
}
