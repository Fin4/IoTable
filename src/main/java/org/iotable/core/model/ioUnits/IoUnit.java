package org.iotable.core.model.ioUnits;


public final class IoUnit {

    public final String symbol;
    public final String description;
    public final String address;
    public final int number;

    private IoUnit() {
        symbol = "";
        description = "";
        address = "";
        number = 0;
    }

    public IoUnit(String symbol, String description, String address, int number) {
        this.symbol = symbol;
        this.description = description;
        this.address = address;
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IoUnit ioUnit = (IoUnit) o;

        if (number != ioUnit.number) return false;
        if (!symbol.equals(ioUnit.symbol)) return false;
        return address.equals(ioUnit.address);

    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + address.hashCode();
        result = 31 * result + number;
        return result;
    }
}
