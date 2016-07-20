package org.iotable.core.codegenerators.simple;

import org.iotable.core.codegenerators.BaseCodeGenerator;
import org.iotable.core.codegenerators.DoCodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.ArrayList;
import java.util.List;

public class SimpleDoCodeGenerator implements DoCodeGenerator {

    private static final BaseCodeGenerator baseGenerator = new BaseCodeGenerator();

    @Override
    public List<String> generateCode(List<DiscreteOutput> discreteOutputs, String template) throws TemplateStringException {

        List<String> strings = new ArrayList<>();

        for (DiscreteOutput discreteOutput : discreteOutputs) {
            String base = baseGenerator.generateCode(discreteOutput.getIoUnit(), template);
            strings.add(base);
        }

        return strings;
    }
}
