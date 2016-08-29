package org.iotable.core.mappers.simple;


import org.iotable.core.mappers.AoMapper;
import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SimpleAoMapper extends BaseMapper implements AoMapper {

    @Override
    public List<String> generateCode(List<AnalogOutput> analogOutputs, String template) throws TemplateStringException {

        return analogOutputs.stream()
                .map(analogOutput -> generateCode(analogOutput.getIoUnit(), template))
                .collect(Collectors.toList());
    }
}
