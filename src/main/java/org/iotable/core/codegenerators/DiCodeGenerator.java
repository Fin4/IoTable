package org.iotable.core.codegenerators;

import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.List;

public interface DiCodeGenerator {

    List<String> generateCode(List<DiscreteInput> discreteInputs, String template) throws TemplateStringException;
}
