package org.iotable.core.model;


import org.iotable.core.model.ioUnits.AnalogInput;
import org.iotable.core.model.ioUnits.AnalogOutput;
import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;

import java.util.Collections;
import java.util.List;

public final class IoTable {

    private final List<AnalogInput> analogInputs;
    private final List<DiscreteInput> discreteInputs;
    private final List<AnalogOutput> analogOutputs;
    private final List<DiscreteOutput> discreteOutputs;

    public IoTable(List<AnalogInput> analogInputs, List<DiscreteInput> discreteInputs,
                   List<AnalogOutput> analogOutputs, List<DiscreteOutput> discreteOutputs) {
        this.analogInputs = analogInputs;
        this.discreteInputs = discreteInputs;
        this.analogOutputs = analogOutputs;
        this.discreteOutputs = discreteOutputs;
    }

    public List<AnalogInput> getAnalogInputs() {
        return Collections.unmodifiableList(analogInputs);
    }

    public List<DiscreteInput> getDiscreteInputs() {
        return Collections.unmodifiableList(discreteInputs);
    }

    public List<AnalogOutput> getAnalogOutputs() {
        return Collections.unmodifiableList(analogOutputs);
    }

    public List<DiscreteOutput> getDiscreteOutputs() {
        return Collections.unmodifiableList(discreteOutputs);
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
