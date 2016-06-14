package org.rldev.iotable.model.ioUnits;


public class DigitalInput extends IoUnit {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DigitalInput)) return false;

        DigitalInput digitalInput = (DigitalInput) o;

        return getSymbol().equals(digitalInput.getSymbol()) ||
                (getNumber() == digitalInput.getNumber()) ||
                getAddress().equals(digitalInput.getAddress());

    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }
}
