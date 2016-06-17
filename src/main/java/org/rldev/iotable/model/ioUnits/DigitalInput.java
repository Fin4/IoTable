package org.rldev.iotable.model.ioUnits;


public class DigitalInput extends IoUnit {

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

        DigitalInput digitalInput = (DigitalInput) o;

        return number == digitalInput.number
                && symbol.equals(digitalInput.symbol)
                && address.equals(digitalInput.address);
    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + number;
        return result;
    }
}
