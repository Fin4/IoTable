package org.rldev.iotable.model.ioUnits;


public class DigitalOutput extends IoUnit {

    @Override
    public String toString() {
        return "DO{" +
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

        DigitalOutput digitalOutput = (DigitalOutput) o;

        return number == digitalOutput.number
                && symbol.equals(digitalOutput.symbol)
                && address.equals(digitalOutput.address);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + number;
        return result;
    }
}
