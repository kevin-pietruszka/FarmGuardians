package tests.main;

import org.junit.Test;
import src.main.Crop;
import src.main.Fertilizer;
import org.junit.Before;
import static org.junit.Assert.assertEquals;

public class TestFertilizer {

    private Fertilizer f;
    private Crop c;

    @Before
    public void setup() {

        f = new Fertilizer();
        c = new Crop("Pumpkin");

    }

    @Test
    public void testInit() {
        assertEquals(f.getType(), "Fertilizer");
    }

    public void testFertilize() {

        c.fertilize();

        assertEquals(c.getFertilizeLevel(), 1);

        assertEquals(c.useFertilizer(), true);

        assertEquals(c.getFertilizeLevel(), 0);
    }

}
