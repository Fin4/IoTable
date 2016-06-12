package org.rldev.iotable.model.ioUnits;


public abstract class IoUnit {

    protected String symbol;
    protected String description;
    protected String address;
    protected int number;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "IoUnit{" +
                "number=" + number +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", symbol='" + symbol + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IoUnit)) return false;

        IoUnit ioUnit = (IoUnit) o;

        return getSymbol().equals(ioUnit.getSymbol());

    }

    @Override
    public int hashCode() {
        return getSymbol().hashCode();
    }
}
