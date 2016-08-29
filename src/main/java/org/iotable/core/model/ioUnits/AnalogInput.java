package org.iotable.core.model.ioUnits;


public final class AnalogInput {

    private final IoUnit ioUnit;
    private final String engUnits;

    public IoUnit getIoUnit() {
        return ioUnit;
    }

    public AnalogInput(IoUnit ioUnit, String engUnits) {
        this.ioUnit = ioUnit;
        this.engUnits = engUnits;
    }

    public String getEngUnits() {
        return engUnits;
    }

    @Override
    public String toString() {
        return "AI{" +
                "symbol='" + ioUnit.symbol + '\'' +
                ", description='" + ioUnit.description + '\'' +
                ", address='" + ioUnit.address + '\'' +
                ", number=" + ioUnit.number + '\'' +
                ", engUnits='" + engUnits + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnalogInput that = (AnalogInput) o;

        return ioUnit.equals(that.ioUnit);

    }

    @Override
    public int hashCode() {
        return ioUnit.hashCode() + "AI".hashCode();
    }
}
