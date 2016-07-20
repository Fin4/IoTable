package org.iotable.enterprise.service;

import org.iotable.core.codegenerators.AiCodeGenerator;
import org.iotable.core.codegenerators.CodeGenerator;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.IoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleCodeGeneratorService implements CodeGeneratorService {

    @Autowired private AiCodeGenerator simpleCodeGenerator;

    @Override
    public List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException {
        return simpleCodeGenerator.generateCode(analogInputs, template);
    }
}