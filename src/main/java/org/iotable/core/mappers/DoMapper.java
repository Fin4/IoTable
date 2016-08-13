package org.iotable.core.mappers;


import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.List;

public interface DoMapper {

    List<String> generateCode(List<DiscreteOutput> discreteOutputs, String template) throws TemplateStringException;
}
