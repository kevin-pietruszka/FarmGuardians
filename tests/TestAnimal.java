import org.junit.Before;
import org.junit.Test;
import src.main.Animal;

import static org.junit.Assert.assertEquals;

public class TestAnimal {

    private Animal animal;

    @Before
    public void setUp() {
        this.animal = new Animal(10.0, "cow");
    }

    @Test
    public void getPrice() {
        assertEquals(animal.getPrice(), 10.0, .01);
    }

    @Test
    public void setPrice() {
        animal.setPrice(20);
        assertEquals(animal.getPrice(), 20.0, .01);
    }

    @Test
    public void getSpecies() {
        assertEquals(animal.getType(), "cow");
    }



}