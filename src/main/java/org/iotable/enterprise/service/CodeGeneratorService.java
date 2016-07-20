package org.iotable.enterprise.service;

import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.IoUnit;

import java.util.List;


public interface CodeGeneratorService {

    List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException;
}
