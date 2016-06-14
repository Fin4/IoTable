package org.rldev.iotable.model.ioUnits;


public class DigitalOutput extends IoUnit {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DigitalOutput)) return false;

        DigitalOutput digitalOutput = (DigitalOutput) o;

        return getSymbol().equals(digitalOutput.getSymbol()) ||
                (getNumber() == digitalOutput.getNumber()) ||
                getAddress().equals(digitalOutput.getAddress());

    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }
}
