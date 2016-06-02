package org.rldev.iotable.model;


import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DigitalInput;
import org.rldev.iotable.model.ioUnits.DigitalOutput;

import java.util.List;

public class IoTable {

    private List<AnalogInput> analogInputs;
    private List<DigitalInput> digitalInputs;
    private List<AnalogOutput> analogOutputs;
    private List<DigitalOutput> digitalOutputs;

    public List<AnalogInput> getAnalogInputs() {
        return analogInputs;
    }

    public void setAnalogInputs(List<AnalogInput> analogInputs) {
        this.analogInputs = analogInputs;
    }

    public List<DigitalInput> getDigitalInputs() {
        return digitalInputs;
    }

    public void setDigitalInputs(List<DigitalInput> digitalInputs) {
        this.digitalInputs = digitalInputs;
    }

    public List<AnalogOutput> getAnalogOutputs() {
        return analogOutputs;
    }

    public void setAnalogOutputs(List<AnalogOutput> analogOutputs) {
        this.analogOutputs = analogOutputs;
    }

    public List<DigitalOutput> getDigitalOutputs() {
        return digitalOutputs;
    }

    public void setDigitalOutputs(List<DigitalOutput> digitalOutputs) {
        this.digitalOutputs = digitalOutputs;
    }

    @Override
    public String toString() {
        return "IoTable{" +
                "analogInputs=" + analogInputs +
                ", digitalInputs=" + digitalInputs +
                ", analogOutputs=" + analogOutputs +
                ", digitalOutputs=" + digitalOutputs +
                '}';
    }
}
