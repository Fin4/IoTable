package org.iotable.core.codegenerators;

import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;

import java.util.Collection;
import java.util.List;

public interface CodeGenerator {

    List<String> generateCode(Collection<? extends IoUnit> ioUnits, String format) throws TemplateStringException;
}
