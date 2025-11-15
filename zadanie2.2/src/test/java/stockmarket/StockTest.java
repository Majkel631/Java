package stockmarket;

import org.junit.jupiter.api.Test;
import org.stockmarket.stockmarket.Stock;

import static org.junit.jupiter.api.Assertions.*;

public class StockTest {

    @Test
    void constructorShouldCreateValidStock() {
        Stock stock = new Stock("CDR", "CD Projekt", 120.5);

        assertEquals("CDR", stock.getSymbol());
        assertEquals("CD Projekt", stock.getName());
        assertEquals(120.5, stock.getInitialPrice());
    }

    @Test
    void constructorShouldRejectInvalidSymbol() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("", "Firma", 100));
    }

    @Test
    void constructorShouldRejectInvalidName() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("CDR", "   ", 100));
    }

    @Test
    void constructorShouldRejectInvalidPrice() {
        assertThrows(IllegalArgumentException.class,
                () -> new Stock("CDR", "Firma", 0));
    }

    @Test
    void stocksWithSameSymbolShouldBeEqual() {
        Stock s1 = new Stock("CDR", "CD Projekt", 100);
        Stock s2 = new Stock("CDR", "Inna nazwa", 500);

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());
    }

    @Test
    void stocksWithDifferentSymbolsShouldNotBeEqual() {
        Stock s1 = new Stock("CDR", "CD Projekt", 100);
        Stock s2 = new Stock("PLW", "PlayWay", 100);

        assertNotEquals(s1, s2);
    }
}
