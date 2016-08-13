package org.iotable.core.mappers;

import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.List;

public interface DiMapper {

    List<String> generateCode(List<DiscreteInput> discreteInputs, String template) throws TemplateStringException;
}
