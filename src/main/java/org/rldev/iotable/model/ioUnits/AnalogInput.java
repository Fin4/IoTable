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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalogInput analogInput = (AnalogInput) o;

        return number == analogInput.number
                && symbol.equals(analogInput.symbol)
                && address.equals(analogInput.address);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + number;
        return result;
    }
}
