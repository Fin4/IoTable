package org.iotable.core.mappers;


import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.List;

public interface AiMapper {

    List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException;
}
