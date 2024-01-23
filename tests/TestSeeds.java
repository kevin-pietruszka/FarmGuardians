import org.junit.Before;
import org.junit.Test;
import src.main.Seeds;

import static org.junit.Assert.assertEquals;

public class TestSeeds {

    private Seeds seeds;

    @Before
    public void setup() {

        this.seeds = new Seeds(10.0, "pumpkin");
    }

    @Test
    public void testInit() {
        assertEquals(this.seeds.getPrice(), 10.0, .01);
        assertEquals(this.seeds.getType(), "pumpkin");
    }


    @Test
    public void setPrice() {
        seeds.setPrice(20.0);
        assertEquals(seeds.getPrice(), 20.0, .01);
    }
    
}
