package org.iotable.core.codegenerators;


import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.List;

public interface AoCodeGenerator {

    List<String> generateCode(List<AnalogOutput> analogOutputs, String template) throws TemplateStringException;
}
