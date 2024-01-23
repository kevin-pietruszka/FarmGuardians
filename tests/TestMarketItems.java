package tests.ui;

import org.junit.Before;
import org.junit.Test;
import src.main.MarketItems;

import static org.junit.Assert.assertEquals;

public class TestMarketItems {

    private MarketItems marketItems;

    @Before
    public void setup() {

        marketItems = new MarketItems("Animal Feed", 100.0);

    }

    @Test
    public void testInitializer() {

        assertEquals(marketItems.getType(), "Animal Feed");
        assertEquals(marketItems.getPrice(), 100.0, .01);

    }

    @Test
    public void testSetPrice() {

        marketItems.setPrice(75.0);
        assertEquals(marketItems.getPrice(), 75.0, .01);

    }

}
