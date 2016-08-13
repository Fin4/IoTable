package org.iotable.core.codegenerators;

import org.iotable.core.Config;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class BaseCodeGenerator {

    public String generateCode(IoUnit ioUnit, String template) throws TemplateStringException {

        //if (!props.values().stream().anyMatch(o -> template.contains(o.toString()))) throw new TemplateStringException();

        return  template
                    .replace(Config.getProperty("unit.map.desc"), ioUnit.description)
                    .replace(Config.getProperty("unit.map.symbol"), ioUnit.symbol.replaceAll(".+-", "").replaceAll("\\.", "_"))
                    .replace(Config.getProperty("unit.map.address"), ioUnit.address.replaceAll("/", "\\."))
                    .replace(Config.getProperty("unit.map.number"), String.valueOf(ioUnit.number));

    }

}
