package tests;

import org.junit.Before;
import org.junit.Test;
import src.main.Crop;

import static org.junit.Assert.assertEquals;

public class TestPlantController {

    private src.main.GameConfigurator gameConfigurator;
    private src.main.ui.controller.PlantController plantController;

    @Before
    public void setup() {

        gameConfigurator = new src.main.GameConfigurator();
        src.main.ui.controller.FarmController.createInitialPlots();

    }

    @Test
    public void testPlant() {
        Crop newCrop = new Crop("Pumpkin");
        plantController.switchToPlantOptions(4);
        plantController.plant(newCrop);

        assertEquals(src.main.ui.controller.FarmController.getPlots().get(3).getCrop(), newCrop);
    }
}
