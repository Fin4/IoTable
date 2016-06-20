package org.rldev.iotable.codegenerators;

import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.codegenerators.exceptions.TemplateStringException;

import java.util.Collection;
import java.util.List;

public interface CodeGenerator {

    List<String> generateCode(Collection<? extends IoUnit> ioUnits, String format) throws TemplateStringException;
}
