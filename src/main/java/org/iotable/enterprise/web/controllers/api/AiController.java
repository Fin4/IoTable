package org.iotable.enterprise.web.controllers.api;

import org.iotable.core.model.IoTable;
import org.iotable.core.model.ioUnits.AnalogInput;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
@SessionAttributes("iotable")
public class AiController {

    @RequestMapping(value = "/aiTable", method = RequestMethod.GET)
    public List<AnalogInput> getAnalogInputs(@ModelAttribute("iotable") IoTable ioTable) {
        return ioTable.getAnalogInputs();
    }
}
