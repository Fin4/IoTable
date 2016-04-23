package org.rldev.iotable;


public class IoUnit {

    private String symbol;

    private String description;

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

    @Override
    public String toString() {
        return "IoUnit{" +
                "symbol='" + symbol + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
