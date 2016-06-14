package org.rldev.iotable.model.ioUnits;


public class AnalogInput extends IoUnit {

    private String engUnits;

    public String getEngUnits() {
        return engUnits;
    }

    public void setEngUnits(String engUnits) {
        this.engUnits = engUnits;
    }

    @Override
    public String toString() {
        return "AI{" +
                "number=" + number +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", symbol='" + symbol + '\'' +
                ", engUnits='" + engUnits + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalogInput)) return false;

        AnalogInput ai = (AnalogInput) o;

        return getSymbol().equals(ai.getSymbol()) ||
                (getNumber() == ai.getNumber()) ||
                getAddress().equals(ai.getAddress());

    }

    @Override
    public int hashCode() {
        int result = symbol.hashCode();
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + number;
        return result;
    }

}
