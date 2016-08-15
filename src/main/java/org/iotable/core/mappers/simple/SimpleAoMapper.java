package org.iotable.core.mappers.simple;


import org.iotable.core.mappers.AoMapper;
import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.ArrayList;
import java.util.List;

public final class SimpleAoMapper extends BaseMapper implements AoMapper {

    @Override
    public List<String> generateCode(List<AnalogOutput> analogOutputs, String template) throws TemplateStringException {

        List<String> strings = new ArrayList<>();

        for (AnalogOutput ao : analogOutputs) {
            String base = generateCode(ao.getIoUnit(), template);
            strings.add(base);
        }

        return strings;
    }
}
