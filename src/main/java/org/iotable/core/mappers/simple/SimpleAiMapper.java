package org.iotable.core.mappers.simple;


import org.iotable.core.Config;
import org.iotable.core.mappers.AiMapper;
import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.List;
import java.util.stream.Collectors;

public final class SimpleAiMapper extends BaseMapper implements AiMapper {

    @Override
    public List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException {

        return analogInputs.stream()
                .map(analogInput -> generateCode(analogInput.getIoUnit(), template)
                        .replaceAll(Config.getProperty("unit.map.engUnits"), analogInput.getEngUnits()))
                .collect(Collectors.toList());
    }
}
