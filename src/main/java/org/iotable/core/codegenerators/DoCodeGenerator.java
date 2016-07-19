package org.iotable.core.codegenerators;


import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.List;

public interface DoCodeGenerator {

    List<String> generateCode(List<DiscreteOutput> discreteOutputs, String template) throws TemplateStringException;
}
