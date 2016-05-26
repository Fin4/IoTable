package org.rldev.iotable.model.mechanisms;


import org.rldev.iotable.model.ioUnits.IoUnit;

import java.util.List;

public class Mechanism {

    private String symbol;

    private String description;

    private List<IoUnit> ioUnits;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public List<IoUnit> getIoUnits() {
        return ioUnits;
    }

    public void setIoUnits(List<IoUnit> ioUnits) {
        this.ioUnits = ioUnits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Mechanism{" +
                "symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                ", ioUnits=" + ioUnits +
                '}';
    }
}
