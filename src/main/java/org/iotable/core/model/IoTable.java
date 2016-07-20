package org.iotable.core.model;


import org.iotable.core.model.ioUnits.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class IoTable {

    private final List<AnalogInput> analogInputs;
    private final List<DiscreteInput> discreteInputs;
    private final List<AnalogOutput> analogOutputs;
    private final List<DiscreteOutput> discreteOutputs;

    private IoTable() {
        analogInputs = new ArrayList<>();
        discreteInputs = new ArrayList<>();
        analogOutputs = new ArrayList<>();
        discreteOutputs = new ArrayList<>();
    }

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

    public List<IoUnit> getAiIoUnits() {
        return Collections.unmodifiableList(analogInputs.stream()
                .map(AnalogInput::getIoUnit)
                .collect(Collectors.toList()));
    }

    public List<IoUnit> getDiIoUnits() {
        return Collections.unmodifiableList(discreteInputs.stream()
                .map(DiscreteInput::getIoUnit)
                .collect(Collectors.toList()));
    }

    public List<IoUnit> getAoIoUnits() {
        return Collections.unmodifiableList(analogOutputs.stream()
                .map(AnalogOutput::getIoUnit)
                .collect(Collectors.toList()));
    }

    public List<IoUnit> getDoIoUnits() {
        return Collections.unmodifiableList(discreteOutputs.stream()
                .map(DiscreteOutput::getIoUnit)
                .collect(Collectors.toList()));
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
