package org.iotable.enterprise.web.controllers.api.requestWrappers;


import org.iotable.core.model.ioUnits.AnalogInput;

import java.util.List;

public class AiMapWrapper {

    private List<AnalogInput> analogInputs;
    private String template;

    public List<AnalogInput> getAnalogInputs() {
        return analogInputs;
    }

    public void setAnalogInputs(List<AnalogInput> analogInputs) {
        this.analogInputs = analogInputs;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }
}
