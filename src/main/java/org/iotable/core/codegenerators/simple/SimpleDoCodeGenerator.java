package org.iotable.core.codegenerators.simple;

import org.iotable.core.codegenerators.DoCodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.List;

public class SimpleDoCodeGenerator implements DoCodeGenerator {

    @Override
    public List<String> generateCode(List<DiscreteOutput> discreteOutputs, String template) throws TemplateStringException {
        return null;
    }
}
