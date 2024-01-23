package tests.ui;

import org.junit.Before;
import org.junit.Test;
import src.main.Crop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestCrop {
    private Crop crop;

    @Before
    public void setup() {

        crop = new Crop("Corn");

    }

    @Test
    public void testInitializer() {

        assertEquals(crop.getType(), "Corn");

    }

    @Test
    public void testPesticide() {
        crop.sprayPesticide();
        assertTrue(crop.getPesticide());
    }


}
