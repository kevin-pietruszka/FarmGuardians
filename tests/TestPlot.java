package tests;

import javafx.scene.paint.Color;
import org.junit.Before;
import org.junit.Test;
import src.main.Crop;
import src.main.Plot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPlot {

    private Plot plot;

    @Before
    public void setup() {

        this.plot = new Plot(2, new Crop("Pumpkin"));

    }

    @Test
    public void testInitializer() {

        assertEquals(plot.getStageOfGrowth(), 2);
        assertEquals(plot.getCrop().getType(), "Pumpkin");
    }

    @Test
    public void testExceedWaterLevel() {
        for (int i = 0; i < 6; i++) {
            plot.water();
        }

        assertEquals(plot.getWaterLevelWidth(), 0, .01);
        assertEquals(plot.getWaterLevelColor(), Color.RED);
        assertEquals(plot.getWaterLevel(), " 0");
    }

    @Test
    public void testUnderWaterLevel() {
        src.main.GameConfigurator.advanceTime();
        plot.grow();
        assertEquals(plot.getWaterLevelWidth(), 0, .01);
        assertEquals(plot.getStageOfGrowth(), -1);
        assertEquals(plot.getWaterLevel(), " 0");
    }

    @Test
    public void testSprayPesticide() {
        plot.sprayPesticide();
        assertTrue(plot.getCrop().getPesticide());
    }



}
