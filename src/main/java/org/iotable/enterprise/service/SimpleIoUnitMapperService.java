package org.iotable.enterprise.service;

import org.iotable.core.mappers.AiMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleIoUnitMapperService implements IoUnitMapperService {

    @Autowired private AiMapper simpleMapper;

    @Override
    public List<String> generateCode(List<AnalogInput> analogInputs, String template) throws TemplateStringException {
        return simpleMapper.generateCode(analogInputs, template);
    }
}