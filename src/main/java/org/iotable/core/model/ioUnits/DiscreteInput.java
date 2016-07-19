package org.iotable.core.model.ioUnits;


public final class DiscreteInput {

    private final IoUnit ioUnit;

    public IoUnit getIoUnit() {
        return ioUnit;
    }

    public DiscreteInput(IoUnit ioUnit) {
        this.ioUnit = ioUnit;
    }

    @Override
    public String toString() {
        return "DI{" +
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

        DiscreteInput that = (DiscreteInput) o;

        return ioUnit.equals(that.ioUnit);

    }

    @Override
    public int hashCode() {
        return ioUnit.hashCode();
    }
}
