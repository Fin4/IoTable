package org.iotable.core.model.mechanisms;


import org.iotable.core.model.ioUnits.*;

import java.util.Collections;
import java.util.List;

public final class Mechanism {

    private final String symbol;
    private final String description;

    private final List<AnalogInput> analogInputs;
    private final List<DiscreteInput> discreteInputs;
    private final List<AnalogOutput> analogOutputs;
    private final List<DiscreteOutput> discreteOutputs;

    public Mechanism(String symbol, String description, List<AnalogInput> analogInputs,
                     List<DiscreteInput> discreteInputs,
                     List<AnalogOutput> analogOutputs,
                     List<DiscreteOutput> discreteOutputs) {
        this.symbol = symbol;
        this.description = description;
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

    public String getSymbol() {
        return symbol;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Mechanism{" +
                "symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", analogInputs=" + analogInputs +
                ", discreteInputs=" + discreteInputs +
                ", analogOutputs=" + analogOutputs +
                ", discreteOutputs=" + discreteOutputs +
                '}';
    }
}
