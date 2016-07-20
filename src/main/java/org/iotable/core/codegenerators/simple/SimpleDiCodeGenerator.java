package org.iotable.core.codegenerators.simple;

import org.iotable.core.codegenerators.BaseCodeGenerator;
import org.iotable.core.codegenerators.DiCodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.ArrayList;
import java.util.List;

public class SimpleDiCodeGenerator implements DiCodeGenerator {

    private static final BaseCodeGenerator baseGenerator = new BaseCodeGenerator();

    @Override
    public List<String> generateCode(List<DiscreteInput> discreteInputs, String template) throws TemplateStringException {

        List<String> strings = new ArrayList<>();

        for (DiscreteInput di : discreteInputs) {
            String base = baseGenerator.generateCode(di.getIoUnit(), template);
            strings.add(base);
        }

        return strings;
    }
}
