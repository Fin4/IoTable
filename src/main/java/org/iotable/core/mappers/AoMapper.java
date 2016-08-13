package org.iotable.core.mappers;


import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.List;

public interface AoMapper {

    List<String> generateCode(List<AnalogOutput> analogOutputs, String template) throws TemplateStringException;
}
