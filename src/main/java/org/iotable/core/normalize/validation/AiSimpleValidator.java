package org.iotable.core.normalize.validation;

import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class AiSimpleValidator extends AbstractIoUnitValidator implements IoUnitValidator<AnalogInput> {

    public List<AnalogInput> validate(List<AnalogInput> analogInputs) {

        super.baseValidate(analogInputs);

        for (AnalogInput analogInput : analogInputs) {

            String engUnits = analogInput.getEngUnits();

            if (engUnits == null || engUnits.trim().isEmpty()) analogInput.setEngUnits("");
            else {
                ArrayList<String> tokens = new ArrayList<>(Arrays.asList(engUnits.split(" ")));
                analogInput.setEngUnits(tokens.get(tokens.size() - 1).trim());
            }
        }

        return analogInputs;
    }
}
