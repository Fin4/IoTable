package org.iotable.core.codegenerators.simple;


import org.iotable.core.codegenerators.AoCodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.List;

public class SimpleAoCodeGenerator implements AoCodeGenerator {

    @Override
    public List<String> generateCode(List<AnalogOutput> analogOutputs, String template) throws TemplateStringException {
        return null;
    }
}
