package tests.ui;

import org.junit.Before;
import org.junit.Test;

public class TestPlantOptionsScreen {

    private src.main.ui.view.PlantOptionsView plantScreen;

    @Before
    public void setup() {
        plantScreen = new src.main.ui.view.PlantOptionsView(null);
    }

    @Test
    public void testConstructor() {
        new src.main.ui.view.FarmScreen(null);
    }

}
