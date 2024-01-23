package ui;

import org.junit.Before;
import org.junit.Test;

public class TestPlantScreen {

    private src.main.ui.view.PlantView plantScreen;

    @Before
    public void setup() {
        plantScreen = new src.main.ui.view.PlantView(null);
    }

    @Test
    public void testConstructor() {
        new src.main.ui.view.FarmScreen(null);
    }

}
