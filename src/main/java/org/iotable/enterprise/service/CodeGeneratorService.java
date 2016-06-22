package org.iotable.enterprise.service;

import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.IoUnit;

import java.util.List;


public interface CodeGeneratorService {

    List<String> generateCode(List<? extends IoUnit> ioUnits, String template) throws TemplateStringException;
}
