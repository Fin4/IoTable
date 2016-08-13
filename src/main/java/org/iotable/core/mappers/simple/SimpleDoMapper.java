package org.iotable.core.mappers.simple;

import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.DoMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.ArrayList;
import java.util.List;

public class SimpleDoMapper implements DoMapper {

    private static final BaseMapper baseGenerator = new BaseMapper();

    @Override
    public List<String> generateCode(List<DiscreteOutput> discreteOutputs, String template) throws TemplateStringException {

        List<String> strings = new ArrayList<>();

        for (DiscreteOutput discreteOutput : discreteOutputs) {
            String base = baseGenerator.generateCode(discreteOutput.getIoUnit(), template);
            strings.add(base);
        }

        return strings;
    }
}
