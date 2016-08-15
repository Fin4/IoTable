package org.iotable.core.mappers.simple;


import org.iotable.core.Config;
import org.iotable.core.mappers.AiMapper;
import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.ArrayList;
import java.util.List;

public class SimpleAiMapper extends BaseMapper implements AiMapper {

    @Override
    public List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException {
        List<String> strings = new ArrayList<>();

        for (AnalogInput ai : analogInputs) {
            String base = generateCode(ai.getIoUnit(), template)
                    .replaceAll(Config.getProperty("unit.map.engUnits"), ai.getEngUnits());
            strings.add(base);
        }

        return strings;
    }
}
