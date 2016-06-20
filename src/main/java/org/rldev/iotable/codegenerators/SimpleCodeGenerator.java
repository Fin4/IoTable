package org.rldev.iotable.codegenerators;

import org.rldev.iotable.model.ioUnits.IoUnit;
import org.rldev.iotable.codegenerators.exceptions.TemplateStringException;

import java.util.Collection;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class SimpleCodeGenerator implements CodeGenerator {

    public Properties props;

    @Override
    public List<String> generateCode(Collection<? extends IoUnit> ioUnits, String template)
            throws TemplateStringException {

        if (props == null) defaultProps();

        if (!props.values().stream().anyMatch(o -> template.contains(o.toString()))) throw new TemplateStringException();

        return ioUnits.stream().map(ioUnit -> template
                    .replace(props.getProperty("description"), ioUnit.getDescription())
                    .replace(props.getProperty("symbol"), ioUnit.getSymbol().replaceAll(".+\\-", "").replaceAll("\\.", "_"))
                    .replace(props.getProperty("address"), ioUnit.getAddress().replaceAll("/", "\\."))
                    .replace(props.getProperty("number"), String.valueOf(ioUnit.getNumber()))
                    .replace("\n", System.lineSeparator()))
                .collect(Collectors.toList());
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
