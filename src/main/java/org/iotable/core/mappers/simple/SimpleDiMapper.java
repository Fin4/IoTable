package org.iotable.core.mappers.simple;

import org.iotable.core.mappers.BaseMapper;
import org.iotable.core.mappers.DiMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.DiscreteInput;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class SimpleDiMapper extends BaseMapper implements DiMapper {

    @Override
    public List<String> generateCode(List<DiscreteInput> discreteInputs, String template) throws TemplateStringException {

        return discreteInputs.stream()
                .map(discreteInput -> generateCode(discreteInput.getIoUnit(), template))
                .collect(Collectors.toList());
    }
}
