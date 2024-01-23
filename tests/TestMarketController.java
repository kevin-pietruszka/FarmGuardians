package ui;

import org.junit.Before;
import org.junit.Test;
import src.main.Animal;
import src.main.GameConfigurator;
import src.main.MarketItems;
import src.main.MarketPlace;
import src.main.Player;
import src.main.ui.controller.MarketController;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestMarketController {

    private GameConfigurator gameConfigurator;
    private MarketPlace marketPlace;
    private MarketItems[] marketItemsArr;
    private MarketController marketController;
    private Player player;

    @Before
    public void setup() {

        gameConfigurator = new GameConfigurator();
        player = new Player("Bob", "", 100.0,
                new ArrayList<Animal>(), new ArrayList<MarketItems>());
        gameConfigurator.setPlayer(player);
        marketController = new MarketController();
        marketItemsArr = new MarketItems[2];
        marketItemsArr[0] = new MarketItems("Pumpkin Seeds", 15.0);
        marketItemsArr[1] = new MarketItems("Animal Feed", 5.0);
        marketPlace = new MarketPlace();
        marketPlace.addItem(marketItemsArr[0]);
        marketPlace.addItem(marketItemsArr[1]);

    }

    @Test
    public void testGetDifficultyVariance() {

        src.main.GameConfigurator.setDifficulty("Egg");
        double variance = marketController.getDifficultyVariance();

        assertEquals(variance, 0.75, .01);

    }

    @Test
    public void testSellPrice() {

        gameConfigurator.setDifficulty("Egg");
        double price = 0.1 + .75 + 5.0;
        double itemPrice = 0.1 + marketController.getDifficultyVariance()
                + marketPlace.findItemPrice("Animal Feed");

        assertEquals(itemPrice, price, 0.1);
    }

}
