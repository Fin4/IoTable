package org.iotable.core.mappers.simple;

import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.DiMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.ArrayList;
import java.util.List;

public class SimpleDiMapper implements DiMapper {

    private static final BaseMapper baseGenerator = new BaseMapper();

    @Override
    public List<String> generateCode(List<DiscreteInput> discreteInputs, String template) throws TemplateStringException {

        List<String> strings = new ArrayList<>();

        for (DiscreteInput di : discreteInputs) {
            String base = baseGenerator.generateCode(di.getIoUnit(), template);
            strings.add(base);
        }

        return strings;
    }
}
