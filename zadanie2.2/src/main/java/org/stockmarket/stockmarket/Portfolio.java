package org.stockmarket.stockmarket;

public class Portfolio {

    private double cash;
    private StockHolding[] holdings;
    private int holdingsCount;
    private final int MAX_HOLDINGS = 10;

    // Statyczna klasa wewnętrzna reprezentująca pozycję w portfelu
    private static class StockHolding {
        private final Stock stock;
        private int quantity;

        public StockHolding(Stock stock, int quantity) {
            this.stock = stock;
            this.quantity = quantity;
        }
    }

    public Portfolio(double initialCash) {
        if (initialCash < 0) {
            throw new IllegalArgumentException("Initial cash cannot be negative");
        }
        this.cash = initialCash;
        this.holdings = new StockHolding[MAX_HOLDINGS];
        this.holdingsCount = 0;
    }

    public double getCash() {
        return cash;
    }

    public int getHoldingsCount() {
        return holdingsCount;
    }

    public void addStock(Stock stock, int quantity) {
        if (stock == null) throw new IllegalArgumentException("Stock cannot be null");
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be positive");

        // Sprawdź, czy akcja już istnieje
        for (int i = 0; i < holdingsCount; i++) {
            if (holdings[i].stock.equals(stock)) {
                holdings[i].quantity += quantity;
                return;
            }
        }

        // Dodaj nową pozycję, jeśli jest miejsce
        if (holdingsCount >= MAX_HOLDINGS) {
            throw new IllegalStateException("Portfolio is full");
        }

        holdings[holdingsCount++] = new StockHolding(stock, quantity);
    }

    public int getStockQuantity(Stock stock) {
        for (int i = 0; i < holdingsCount; i++) {
            if (holdings[i].stock.equals(stock)) {
                return holdings[i].quantity;
            }
        }
        return 0;
    }
    public Stock[] getStocks() {
        Stock[] stocks = new Stock[holdingsCount];
        for (int i = 0; i < holdingsCount; i++) {
            stocks[i] = holdings[i].stock;
        }
        return stocks;
    }

    public double calculateStockValue() {
        double total = 0;
        for (int i = 0; i < holdingsCount; i++) {
            total += holdings[i].quantity * holdings[i].stock.getInitialPrice();
        }
        return total;
    }

    public double calculateTotalValue() {
        return cash + calculateStockValue();
    }
}