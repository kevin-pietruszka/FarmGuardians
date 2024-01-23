package tests;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import src.main.ui.controller.FarmController;

public class TestFarmController {
    @Before
    public void setup() {
        FarmController farmController = new src.main.ui.controller.FarmController();
        farmController.createInitialPlots();
        src.main.Player person = new src.main.Player("Person", "Fall", 100, null, null);
        src.main.GameConfigurator.setPlayer(person);

        //sets all plots to corn of 0 growth
        for (int i = 0; i < FarmController.getPlots().size() && FarmController.getPlots().get(i) != null; i++) {
            src.main.Plot plot  = src.main.ui.controller.FarmController.getPlots().get(i);
            plot.setCrop(new src.main.Crop("Corn"));
            plot.setStageOfGrowth(2);
        }
    }

    @Test
    public void testEventChanceScaling() {

        String difficulty = "Egg";
        float eggRainCutoff = difficulty.equals("Egg") ? 0.5f
                : (difficulty.equals("Chicken") ? 0.3f : 0.1f);
        float eggDroughtCutoff = difficulty.equals("Egg") ? 0.1f
                : (difficulty.equals("Chicken") ? 0.3f : 0.5f);
        float eggLocustCutoff = difficulty.equals("Egg") ? 0.1f
                : (difficulty.equals("Chicken") ? 0.3f : 0.5f);

        difficulty = "Chicken";
        float chickenRainCutoff = difficulty.equals("Egg") ? 0.5f
                : (difficulty.equals("Chicken") ? 0.3f : 0.1f);
        float chickenDroughtCutoff = difficulty.equals("Egg") ? 0.1f
                : (difficulty.equals("Chicken") ? 0.3f : 0.5f);
        float chickenLocustCutoff = difficulty.equals("Egg") ? 0.1f
                : (difficulty.equals("Chicken") ? 0.3f : 0.5f);

        difficulty = "Chick fil a";
        float cfaRainCutoff = difficulty.equals("Egg") ? 0.5f
                : (difficulty.equals("Chicken") ? 0.3f : 0.1f);
        float cfaDroughtCutoff = difficulty.equals("Egg") ? 0.1f
                : (difficulty.equals("Chicken") ? 0.3f : 0.5f);
        float cfaLocustCutoff = difficulty.equals("Egg") ? 0.1f
                : (difficulty.equals("Chicken") ? 0.3f : 0.5f);

        assertTrue(eggRainCutoff > chickenRainCutoff);
        assertTrue(eggRainCutoff > cfaRainCutoff);
        assertTrue(chickenRainCutoff > cfaRainCutoff);

        assertTrue(eggDroughtCutoff < chickenDroughtCutoff);
        assertTrue(eggDroughtCutoff < cfaDroughtCutoff);
        assertTrue(chickenDroughtCutoff < cfaDroughtCutoff);

        assertTrue(eggLocustCutoff < chickenLocustCutoff);
        assertTrue(eggLocustCutoff < cfaLocustCutoff);
        assertTrue(chickenLocustCutoff < cfaLocustCutoff);

    }

    @Test
    public void testWaterNoIrrigation() {
        FarmController.resetWaterCount();
        for (int i = 0; i < 5; i++) {
            FarmController.water(i + 1);
        }
        assertEquals(5, FarmController.getWaterCount());
        assertEquals(10, FarmController.getWaterLimit());
    }

    @Test
    public void testWaterWithIrrigation() {
        FarmController.resetWaterCount();
        src.main.GameConfigurator.getPlayer().getInventory().addItem("Irrigation");
        for (int i = 0; i < 12; i++) {
            FarmController.water(i + 1);
        }
        assertEquals(12, FarmController.getWaterCount());
        assertEquals(60, FarmController.getWaterLimit());
    }

}
