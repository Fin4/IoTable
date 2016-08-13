package org.iotable.core.mappers;

import org.iotable.core.Config;
import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.mappers.exceptions.TemplateStringException;

public class BaseMapper {

    public String generateCode(IoUnit ioUnit, String template) throws TemplateStringException {

        //if (!props.values().stream().anyMatch(o -> template.contains(o.toString()))) throw new TemplateStringException();

        return  template
                    .replace(Config.getProperty("unit.map.desc"), ioUnit.description)
                    .replace(Config.getProperty("unit.map.symbol"), ioUnit.symbol.replaceAll(".+-", "").replaceAll("\\.", "_"))
                    .replace(Config.getProperty("unit.map.address"), ioUnit.address.replaceAll("/", "\\."))
                    .replace(Config.getProperty("unit.map.number"), String.valueOf(ioUnit.number));

    }

}
