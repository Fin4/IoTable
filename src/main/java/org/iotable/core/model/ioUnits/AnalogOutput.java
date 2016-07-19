package org.iotable.core.model.ioUnits;

public final class AnalogOutput  {

    private final IoUnit ioUnit;

    public IoUnit getIoUnit() {
        return ioUnit;
    }

    public AnalogOutput(IoUnit ioUnit) {
        this.ioUnit = ioUnit;
    }

    @Override
    public String toString() {
        return "AO{" +
                "symbol='" + ioUnit.symbol + '\'' +
                ", description='" + ioUnit.description + '\'' +
                ", address='" + ioUnit.address + '\'' +
                ", number=" + ioUnit.number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalogOutput that = (AnalogOutput) o;

        return ioUnit.equals(that.ioUnit);

    }

    @Override
    public int hashCode() {
        return ioUnit.hashCode();
    }
}
