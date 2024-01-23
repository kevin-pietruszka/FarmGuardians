package tests.ui;

import org.junit.Before;
import org.junit.Test;
import src.main.GameConfigurator;
import src.main.ui.controller.FarmController;
import src.main.ui.view.FarmScreen;

import static org.junit.Assert.assertEquals;

public class TestFarmScreen {

    private FarmScreen farmScreen;

    @Before
    public void setup() {
        farmScreen = new FarmScreen(null);
    }

    @Test
    public void testConstructor() {
        new FarmScreen(null);
    }


    @Test
    public void testAdvanceTime() {

        String s = FarmController.advanceTime(null);
        assertEquals(GameConfigurator.getTime(), 1);
        assertEquals(s, "Time: 1 day");
        s = FarmController.advanceTime(null);
        assertEquals(s, "Time: 2 days");

    }

}
