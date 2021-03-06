package org.iotable.core.mappers.simple;

import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.DoMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SimpleDoMapper extends BaseMapper implements DoMapper {

    @Override
    public List<String> generateCode(List<DiscreteOutput> discreteOutputs, String template) throws TemplateStringException {

        return discreteOutputs.stream()
                .map(discreteOutput -> generateCode(discreteOutput.getIoUnit(), template))
                .collect(Collectors.toList());
    }
}
