package ui;

import org.junit.Before;
import org.junit.Test;
import src.main.MarketPlace;
import src.main.ui.view.MarketView;
import src.main.ui.controller.MarketController;
import src.main.Pesticide;
import src.main.Seeds;

import static org.junit.Assert.assertTrue;

public class TestMarket {
    private MarketView marketScreen;
    private src.main.MarketPlace market;
    private MarketController controller;

    @Before
    public void setup() {
        src.main.Player person = new src.main.Player("Person", "Fall", 100, null, null);
        src.main.GameConfigurator.setPlayer(person);
        src.main.GameConfigurator.setDifficulty("Egg");

        marketScreen = new MarketView(null);
        market = new MarketPlace();
        controller = new MarketController();
        market.addItem(new Pesticide());
        market.addItem(new Seeds(0.5, "Pumpkin Seeds"));
        market.getItem(0).setPrice(controller.getPrice("Pesticide", market));
    }


    @Test
    public void testPesticidePrice() {
        double pesticidePrice = controller.getPrice("Pumpkin: Pesticide Protected", market);
        double regularPrice = controller.getPrice("Pumpkin", market);

        assertTrue(regularPrice - pesticidePrice > 0);
        assertTrue(pesticidePrice - regularPrice < 0);


    }

}
