package tests;

import org.junit.Before;
import org.junit.Test;
import src.main.Crop;
import src.main.MarketPlace;
import src.main.Player;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TestPlayer {

    private Player player;

    @Before
    public void setup() {

        player = new Player("Player1", "Spring", 100d,
                new ArrayList<>(), new ArrayList<>());

    }

    @Test
    public void testInitializer() {

        assertEquals(player.getName(), "Player1");
        assertEquals(player.getSeason(), "Spring");
        assertEquals(player.getMoney(), 100.0, .01);

    }

    @Test
    public void testHarvest() {

        player.harvest(new Crop("Potato"));

    }

    @Test
    public void testBuy() {

        player.buy(new MarketPlace());

    }

}
