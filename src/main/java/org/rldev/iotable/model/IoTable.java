package org.rldev.iotable.model;


import org.rldev.iotable.model.ioUnits.AnalogInput;
import org.rldev.iotable.model.ioUnits.AnalogOutput;
import org.rldev.iotable.model.ioUnits.DiscreteInput;
import org.rldev.iotable.model.ioUnits.DiscreteOutput;

import java.util.List;

public class IoTable {

    private List<AnalogInput> analogInputs;
    private List<DiscreteInput> discreteInputs;
    private List<AnalogOutput> analogOutputs;
    private List<DiscreteOutput> discreteOutputs;

    public List<AnalogInput> getAnalogInputs() {
        return analogInputs;
    }

    public void setAnalogInputs(List<AnalogInput> analogInputs) {
        this.analogInputs = analogInputs;
    }

    public List<DiscreteInput> getDiscreteInputs() {
        return discreteInputs;
    }

    public void setDiscreteInputs(List<DiscreteInput> discreteInputs) {
        this.discreteInputs = discreteInputs;
    }

    public List<AnalogOutput> getAnalogOutputs() {
        return analogOutputs;
    }

    public void setAnalogOutputs(List<AnalogOutput> analogOutputs) {
        this.analogOutputs = analogOutputs;
    }

    public List<DiscreteOutput> getDiscreteOutputs() {
        return discreteOutputs;
    }

    public void setDiscreteOutputs(List<DiscreteOutput> discreteOutputs) {
        this.discreteOutputs = discreteOutputs;
    }

    @Override
    public String toString() {
        return "IoTable{" +
                "analogInputs=" + analogInputs +
                ", discreteInputs=" + discreteInputs +
                ", analogOutputs=" + analogOutputs +
                ", discreteOutputs=" + discreteOutputs +
                '}';
    }
}
