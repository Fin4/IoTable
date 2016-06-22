package org.iotable.core.model.ioUnits;


public class DiscreteOutput extends IoUnit {

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

        DiscreteOutput discreteOutput = (DiscreteOutput) o;

        return number == discreteOutput.number
                && symbol.equals(discreteOutput.symbol)
                && address.equals(discreteOutput.address);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + number;
        return result;
    }
}
