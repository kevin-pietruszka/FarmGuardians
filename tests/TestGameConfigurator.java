package tests;

import org.junit.Before;
import org.junit.Test;
import src.main.GameConfigurator;
import src.main.Player;
import src.main.ui.controller.FarmController;

import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestGameConfigurator {

    private ArrayList<src.main.Plot> plots;

    @Before
    public void setup() {
        FarmController.createInitialPlots();

        plots = FarmController.getPlots();

        for (src.main.Plot p :
                plots) {

            if (p!=null) p.setStageOfGrowth(-1);

        }
    }

    @Test
    public void testSetAndGetPlayer() {
        Player kevin = new Player("Kevin", "Fall", 100, null, null);
        GameConfigurator.setPlayer(kevin);
        Player temp = GameConfigurator.getPlayer();
        assertEquals(temp, kevin);
    }

    @Test
    public void testSetDifficulty() {
        GameConfigurator.setDifficulty("Egg");
        assertEquals(GameConfigurator.getPlayer().getMoney(), 100d, 0.1);
    }

    @Test
    public void testAdvanceTime() {

        GameConfigurator.advanceTime();
        assertEquals(GameConfigurator.getTime(), 1);

    }

    @Test
    public void testCheckWin() {

        FarmController.createInitialPlots();

        Player p = new Player();
        p.setMoney(1000);
        GameConfigurator.setPlayer(p);
        assertTrue(GameConfigurator.checkWin());

        p.setMoney(0);
        GameConfigurator.setPlayer(p);
        assertFalse(GameConfigurator.checkWin());

    }

    @Test
    public void testCheckLoss() {

        Player p = new Player();
        p.setMoney(100.1);
        GameConfigurator.setPlayer(p);
        assertFalse(GameConfigurator.checkLoss());

        p.setMoney(0);
        GameConfigurator.setPlayer(p);
        assertTrue(GameConfigurator.checkLoss());

    }

}
