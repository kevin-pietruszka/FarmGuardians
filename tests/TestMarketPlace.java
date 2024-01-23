package tests;

import org.junit.Before;
import org.junit.Test;
import src.main.MarketItems;
import src.main.MarketPlace;


import static org.junit.Assert.assertEquals;


public class TestMarketPlace {

    private MarketPlace marketPlace;
    private MarketItems[] itemArr;

    @Before
    public void setup() {
        itemArr = new MarketItems[3];
        itemArr[0] = new MarketItems("pumpkin", 15.0);
        itemArr[1] = new MarketItems("potato", 7.0);
        itemArr[2] = new MarketItems("animal feed", 5.0);
        
        marketPlace = new MarketPlace();
    }

    @Test
    public void add() {
        marketPlace.addItem(itemArr[0]);
        marketPlace.addItem(itemArr[1]);
        marketPlace.addItem(itemArr[2]);
        assertEquals(marketPlace.getItem(0), itemArr[0]);
        assertEquals(marketPlace.getItem(1), itemArr[1]);
        assertEquals(marketPlace.getItem(2), itemArr[2]);
    }

    @Test
    public void remove() {
        marketPlace.addItem(itemArr[0]);
        marketPlace.addItem(itemArr[1]);
        marketPlace.addItem(itemArr[2]);

        assertEquals(marketPlace.removeItem(0), itemArr[0]);
        assertEquals(marketPlace.getItem(0), itemArr[1]);
        assertEquals(marketPlace.getItem(1), itemArr[2]);

    }

}
