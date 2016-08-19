package org.iotable.enterprise.web.controllers.api;

import org.iotable.core.mappers.AiMapper;
import org.iotable.core.mappers.exceptions.TemplateStringException;
import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.enterprise.web.controllers.api.requestWrappers.AiMapWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@SessionAttributes("iotable")
public class AiController {

    @Autowired
    private AiMapper aiMapper;

    @RequestMapping(value = "/aiTable", method = RequestMethod.GET)
    public List<AnalogInput> getAnalogInputs(@ModelAttribute("iotable") IoTable ioTable) {
        return ioTable.getAnalogInputs();
    }

    @RequestMapping(value = "/aiTable/mapper", method = RequestMethod.POST)
    public List<String> mapAnalogInputs(@RequestBody AiMapWrapper aiMapWrapper) {

        List<String> strings;
        try {
            strings = aiMapper.generateCode(aiMapWrapper.getAnalogInputs(), aiMapWrapper.getTemplate());
        } catch (TemplateStringException e) {
            strings = new ArrayList<>();
        }
        return strings;
    }
}
