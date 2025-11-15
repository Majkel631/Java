package org.stockmarket.stockmarket;

import java.util.Objects;

public class Stock {

    private final String symbol;
    private final  String name;
    private final double initialPrice;

    public Stock(String symbol, String name, double initialPrice) {
        if (symbol == null || symbol.isBlank()){
            throw new IllegalArgumentException("symbol cannot by null or empty");
        }
        if (name == null || name.isBlank()){
            throw new IllegalArgumentException("name cannot by null or empty");
        }
        if (initialPrice <= 0){
            throw new IllegalArgumentException("Initial price must be positive");
        }
        this.symbol = symbol;
        this.name = name;
        this.initialPrice = initialPrice;
    }
    

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Stock stock = (Stock) o;
        return Objects.equals(symbol, stock.symbol);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(symbol);
    }

    public String getSymbol() {
        return symbol;
    }

    public String getName() {
        return name;
    }

    public double getInitialPrice() {
        return initialPrice;
    }
}
