package org.iotable.core.codegenerators.simple;


import org.iotable.core.codegenerators.AoCodeGenerator;
import org.iotable.core.codegenerators.BaseCodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.ArrayList;
import java.util.List;

public class SimpleAoCodeGenerator implements AoCodeGenerator {

    private static final BaseCodeGenerator baseGenerator = new BaseCodeGenerator();

    @Override
    public List<String> generateCode(List<AnalogOutput> analogOutputs, String template) throws TemplateStringException {

        List<String> strings = new ArrayList<>();

        for (AnalogOutput ao : analogOutputs) {
            String base = baseGenerator.generateCode(ao.getIoUnit(), template);
            strings.add(base);
        }

        return strings;
    }
}
