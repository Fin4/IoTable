package org.iotable.core.codegenerators;


import org.iotable.core.codegenerators.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.IoUnit;

import java.util.Collection;
import java.util.List;

public class AiCodeGenerator implements CodeGenerator {
    @Override
    public List<String> generateCode(Collection<? extends IoUnit> ioUnits, String template) throws TemplateStringException {
        return  null;
    }
}
