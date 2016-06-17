package org.rldev.iotable.model.ioUnits;

public class AnalogOutput extends IoUnit {

    @Override
    public String toString() {
        return "AO{" +
                "number=" + number +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalogOutput analogOutput = (AnalogOutput) o;

        return number == analogOutput.number
                && symbol.equals(analogOutput.symbol)
                && address.equals(analogOutput.address);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + number;
        return result;
    }
}
