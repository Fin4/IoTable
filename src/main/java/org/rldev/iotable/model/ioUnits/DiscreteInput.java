package org.rldev.iotable.model.ioUnits;


public class DiscreteInput extends IoUnit {

    @Override
    public String toString() {
        return "DI{" +
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

        DiscreteInput discreteInput = (DiscreteInput) o;

        return number == discreteInput.number
                && symbol.equals(discreteInput.symbol)
                && address.equals(discreteInput.address);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + number;
        return result;
    }
}
