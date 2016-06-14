package org.rldev.iotable.model.ioUnits;

public class AnalogOutput extends IoUnit {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalogOutput)) return false;

        AnalogOutput ao = (AnalogOutput) o;

        return getSymbol().equals(ao.getSymbol()) ||
                (getNumber() == ao.getNumber()) ||
                getAddress().equals(ao.getAddress());

    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }
}
