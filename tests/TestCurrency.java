import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestCurrency {

    private src.main.Currency currency;

    @Before
    public void setup() {
        currency = new src.main.Currency("USD", 100);
    }

    @Test
    public void testConstructor() {
        new src.main.Currency("Roubles", 10000);
    }

    @Test
    public void testGetAmount() {
        assertEquals(currency.getAmount(), 100, 0.1);
    }

    @Test
    public void testGetType() {
        assertEquals("USD", currency.getType());
    }

    @Test
    public void testAddAmount() {
        currency.addAmount(50);
        assertEquals(150, currency.getAmount(), 0.1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtractAmount() {
        currency.subtractAmount(99);
        assertEquals(1, currency.getAmount(), 0.1);
        currency.subtractAmount(2);
    }
}
