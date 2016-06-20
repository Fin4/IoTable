package org.rldev.service;

import org.rldev.iotable.codegenerators.CodeGenerator;
import org.rldev.iotable.codegenerators.exceptions.TemplateStringException;
import org.rldev.iotable.model.ioUnits.IoUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleCodeGeneratorService implements CodeGeneratorService {

    @Autowired private CodeGenerator simpleCodeGenerator;

    @Override
    public List<String> generateCode(List<? extends IoUnit> ioUnits, String template) throws TemplateStringException {
        return simpleCodeGenerator.generateCode(ioUnits, template);
    }
}