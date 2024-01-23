import org.junit.Before;
import org.junit.Test;
import src.main.GameState;

import static org.junit.Assert.assertEquals;

public class TestGameState {

    private GameState state;

    @Before
    public void setup() {
        state = new GameState(2, 10, 75.2, "Spring", 0);
    }

    @Test
    public void getFrequency() {
        assertEquals(state.getPrecipitationFrequency(), 2);
    }

    @Test
    public void setFrequency() {
        state.setPrecipitationFrequency(5);
        assertEquals(state.getPrecipitationFrequency(), 5);
    }

    @Test
    public void getAmount() {
        assertEquals(state.getPrecipitationAmount(), 10.0, .01);
    }

    @Test
    public void setAmount() {
        state.setPrecipitationAmount(5);
        assertEquals(state.getPrecipitationAmount(), 5.0, .01);
    }

    @Test
    public void getTemperature() {
        assertEquals(state.getTemperature(), 75.2, .01);
    }

    @Test
    public void setTemperature() {
        state.setTemperature(80.0);
        assertEquals(state.getTemperature(), 80.0, .01);
    }

    @Test
    public void getSeason() {
        assertEquals(state.getSeason(), "Spring");
    }

    @Test
    public void setSeason() {
        state.setSeason("Summer");
        assertEquals(state.getSeason(), "Summer");
    }

    @Test
    public void getDay() {
        assertEquals(state.getDay(), 0);
    }

    @Test
    public void setDay() {
        state.setDay(1);
        assertEquals(state.getDay(), 1);
    }
}