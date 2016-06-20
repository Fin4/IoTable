package org.rldev.service;

import org.rldev.iotable.codegenerators.exceptions.TemplateStringException;
import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;


public interface CodeGeneratorService {

    List<String> generateCode(List<? extends IoUnit> ioUnits, String template) throws TemplateStringException;
}
