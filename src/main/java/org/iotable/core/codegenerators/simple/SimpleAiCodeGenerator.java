package org.iotable.core.codegenerators.simple;


import org.iotable.core.codegenerators.AiCodeGenerator;
import org.iotable.core.codegenerators.BaseCodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.ArrayList;
import java.util.List;

public class SimpleAiCodeGenerator implements AiCodeGenerator {

    private static final BaseCodeGenerator baseGenerator = new BaseCodeGenerator();

    @Override
    public List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException {
        List<String> strings = new ArrayList<>();

        for (AnalogInput ai : analogInputs) {
            String base = baseGenerator.generateCode(ai.getIoUnit(), template)
                    .replaceAll(baseGenerator.props.getProperty("engUnits"), ai.getEngUnits());
            strings.add(base);
        }

        return strings;
    }
}
