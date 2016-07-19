package org.iotable.core.codegenerators;


import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.List;

public interface AiCodeGenerator {

    List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException;
}
