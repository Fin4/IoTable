package org.iotable.core.codegenerators.simple;

import org.iotable.core.codegenerators.DiCodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.List;

public class SimpleDiCodeGenerator implements DiCodeGenerator {

    @Override
    public List<String> generateCode(List<DiscreteInput> discreteInputs, String template) throws TemplateStringException {
        return null;
    }
}
