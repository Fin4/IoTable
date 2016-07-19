package org.iotable.core.codegenerators;

import org.iotable.core.model.ioUnits.IoUnit;
import org.iotable.core.codegenerators.exceptions.TemplateStringException;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class BaseCodeGenerator {

    public Properties props;

    public String generateCode(IoUnit ioUnit, String template) throws TemplateStringException {

        if (props == null) defaultProps();

        if (!props.values().stream().anyMatch(o -> template.contains(o.toString()))) throw new TemplateStringException();

        return  template
                    .replace(props.getProperty("description"), ioUnit.description)
                    .replace(props.getProperty("symbol"), ioUnit.symbol.replaceAll(".+\\-", "").replaceAll("\\.", "_"))
                    .replace(props.getProperty("address"), ioUnit.address.replaceAll("/", "\\."))
                    .replace(props.getProperty("number"), String.valueOf(ioUnit.number));
                    //.replace("\n", System.lineSeparator())

    }

    private void defaultProps() {

        props = new Properties();

        props.setProperty("number", "%number%");
        props.setProperty("address", "%address%");
        props.setProperty("symbol", "%symbol%");
        props.setProperty("description", "%description%");
        props.setProperty("engUnits", "%engUnits%");
    }
}
