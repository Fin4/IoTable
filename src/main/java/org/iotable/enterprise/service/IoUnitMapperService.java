package org.iotable.enterprise.service;

import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.List;


public interface IoUnitMapperService {

    List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException;
}
