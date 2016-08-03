package org.iotable.core.model.mechanisms;


import org.iotable.core.model.ioUnits.DiscreteInput;
import org.iotable.core.model.ioUnits.DiscreteOutput;

public final class DiscreteGate {

    private final DiscreteInput openedState;
    private final DiscreteInput closedState;
    private final DiscreteOutput openCmd;
    private final DiscreteOutput closeCmd;

    public DiscreteGate(DiscreteInput openedState, DiscreteInput closedState, DiscreteOutput openCmd, DiscreteOutput closeCmd) {
        this.openedState = openedState;
        this.closedState = closedState;
        this.openCmd = openCmd;
        this.closeCmd = closeCmd;
    }

    public DiscreteInput getOpenedState() {
        return openedState;
    }

    public DiscreteInput getClosedState() {
        return closedState;
    }

    public DiscreteOutput getOpenCmd() {
        return openCmd;
    }

    public DiscreteOutput getCloseCmd() {
        return closeCmd;
    }
}
