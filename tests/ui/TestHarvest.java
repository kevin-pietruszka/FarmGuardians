package tests.ui;


import main.ui.controller.HarvestController;
import org.junit.Before;
import org.junit.Test;
import src.main.Crop;
import src.main.Plot;
import src.main.ui.controller.FarmController;


import static org.junit.Assert.assertEquals;

public class TestHarvest {

    @Before
    public void setup() {

        FarmController farmController = new FarmController();
        farmController.createInitialPlots();
        src.main.Player person = new src.main.Player("Person", "Fall", 100, null, null);
        src.main.GameConfigurator.setPlayer(person);

        //sets all plots to corn of 0 growth
        for (int i = 0; i < FarmController.getPlots().size() && FarmController.getPlots().get(i) != null; i++) {
            src.main.Plot plot  = FarmController.getPlots().get(i);
            plot.setCrop(new Crop("Corn"));
            plot.setStageOfGrowth(0);
        }
    }

    @Test
    public void testFailedHarvest() {
        HarvestController.resetHarvestCount();
        src.main.GameConfigurator.advanceTime();
        Plot plot = FarmController.getPlots().get(0);
        HarvestController.harvest(1);
        assertEquals(plot.getCrop().getType(), "Corn");
        assertEquals(plot.getStageOfGrowth(), 0);

        HarvestController.resetHarvestCount();
        plot.setStageOfGrowth(1);
        HarvestController.harvest(1);
        assertEquals(plot.getCrop().getType(), "Corn");
        assertEquals(plot.getStageOfGrowth(), 1);

    }

    @Test
    public void testHarvestNoTractor() {
        HarvestController.resetHarvestCount();
        Plot plot = FarmController.getPlots().get(0);
        plot.setStageOfGrowth(2);
        HarvestController.harvest(1);
        assertEquals(1, HarvestController.getHarvestCount());
        assertEquals(1, HarvestController.getHarvestLimit());
    }

    @Test
    public void testHarvestWithTractor() {
        HarvestController.resetHarvestCount();
        src.main.GameConfigurator.getPlayer().getInventory().addItem("Tractor");
        Plot plot = FarmController.getPlots().get(0);
        plot.setStageOfGrowth(2);
        plot = FarmController.getPlots().get(1);
        plot.setStageOfGrowth(2);
        plot = FarmController.getPlots().get(2);
        plot.setStageOfGrowth(2);
        HarvestController.harvest(1);
        HarvestController.harvest(2);
        HarvestController.harvest(3);
        assertEquals(3, HarvestController.getHarvestCount());
        assertEquals(5, HarvestController.getHarvestLimit());
    }


    
}
