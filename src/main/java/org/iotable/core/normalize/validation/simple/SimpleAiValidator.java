package org.iotable.core.normalize.validation.simple;

import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.normalize.validation.AiValidator;
import org.iotable.core.normalize.validation.BaseIoUnitValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SimpleAiValidator implements AiValidator {

    private static final BaseIoUnitValidator baseValidator = new BaseIoUnitValidator();

    private AiValidator validator;

    public SimpleAiValidator(AiValidator validator) {
        this.validator = validator;
    }

    public SimpleAiValidator() {
    }

    public List<AnalogInput> validate(List<AnalogInput> analogInputs) {

        List<AnalogInput> validAis = new ArrayList<>();

        for (AnalogInput analogInput : analogInputs) {

            IoUnit ioUnit = baseValidator.validate(analogInput.getIoUnit());

            String engUnits = analogInput.getEngUnits();

            if (engUnits == null || engUnits.trim().isEmpty()) engUnits = "";
            else {
                ArrayList<String> tokens = new ArrayList<>(Arrays.asList(engUnits.split(" ")));
                engUnits = tokens.get(tokens.size() - 1).trim();
            }

            validAis.add(new AnalogInput(ioUnit, engUnits));
        }

        if (validator != null) validAis = validator.validate(validAis);

        return validAis;
    }
}