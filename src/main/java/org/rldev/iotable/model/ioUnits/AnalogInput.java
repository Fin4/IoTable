package org.rldev.iotable.model.ioUnits;


public class AnalogInput extends IoUnit {

    private String engUnits;

    public String getEngUnits() {
        return engUnits;
    }

    public void setEngUnits(String engUnits) {
        this.engUnits = engUnits;
    }

    @Override
    public String toString() {
        return "AI{" +
                "number=" + number +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", symbol='" + symbol + '\'' +
                ", engUnits='" + engUnits + '\'' +
                '}';
    }

}
