package stockmarket;

import org.junit.jupiter.api.Test;
import org.stockmarket.stockmarket.Portfolio;
import org.stockmarket.stockmarket.Stock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PortfolioTest {

    @Test
    void emptyPortfolioShouldHaveCorrectCashAndZeroHoldings() {
        Portfolio portfolio = new Portfolio(1000);
        assertEquals(1000, portfolio.getCash());
        assertEquals(0, portfolio.getHoldingsCount());
        assertEquals(0, portfolio.calculateStockValue());
        assertEquals(1000, portfolio.calculateTotalValue());
    }

    @Test
    void addingNewStockShouldIncreaseHoldings() {
        Portfolio portfolio = new Portfolio(1000);
        Stock stock = new Stock("CDR", "CD Projekt", 120);
        portfolio.addStock(stock, 5);

        assertEquals(1, portfolio.getHoldingsCount());
        assertEquals(5, portfolio.getStockQuantity(stock));
        assertEquals(600, portfolio.calculateStockValue());
        assertEquals(1600, portfolio.calculateTotalValue());
    }

    @Test
    void addingSameStockMultipleTimesShouldSumQuantities() {
        Portfolio portfolio = new Portfolio(1000);
        Stock stock = new Stock("CDR", "CD Projekt", 120);
        portfolio.addStock(stock, 3);
        portfolio.addStock(stock, 2);

        assertEquals(1, portfolio.getHoldingsCount());
        assertEquals(5, portfolio.getStockQuantity(stock));
        assertEquals(600, portfolio.calculateStockValue());
        assertEquals(1600, portfolio.calculateTotalValue());
    }

    @Test
    void addingDifferentStocksShouldCreateSeparatePositions() {
        Portfolio portfolio = new Portfolio(1000);
        Stock s1 = new Stock("CDR", "CD Projekt", 120);
        Stock s2 = new Stock("PLW", "PlayWay", 50);

        portfolio.addStock(s1, 2);
        portfolio.addStock(s2, 4);

        assertEquals(2, portfolio.getHoldingsCount());
        assertEquals(2, portfolio.getStockQuantity(s1));
        assertEquals(4, portfolio.getStockQuantity(s2));
        assertEquals(2*120 + 4*50, portfolio.calculateStockValue());
        assertEquals(1000 + 2*120 + 4*50, portfolio.calculateTotalValue());
    }

    @Test
    void addingStocksBeyondMaxShouldThrowException() {
        Portfolio portfolio = new Portfolio(1000);
        for (int i = 0; i < 10; i++) {
            portfolio.addStock(new Stock("S" + i, "Company" + i, 10), 1);
        }

        assertThrows(IllegalStateException.class,
                () -> portfolio.addStock(new Stock("S10", "Company10", 10), 1));
    }

    @Test
    void getStockQuantityForNonexistentStockShouldReturnZero() {
        Portfolio portfolio = new Portfolio(1000);
        Stock stock = new Stock("CDR", "CD Projekt", 120);
        assertEquals(0, portfolio.getStockQuantity(stock));
    }
}
