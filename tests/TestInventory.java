package tests;

import org.junit.Before;
import org.junit.Test;
import src.main.Inventory;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;

public class TestInventory {

    private Inventory<String> inventory;
    private static final int MAX_ITEMS = 15;

    @Before
    public void setup() {

        inventory = new Inventory<>(MAX_ITEMS);

    }

    @Test
    public void testMaxItems() {

        inventory.addItem("1");
        inventory.addItem("2");
        inventory.addItem("3");
        inventory.addItem("4");
        inventory.addItem("5");
        inventory.addItem("6");
        inventory.addItem("7");
        inventory.addItem("8");
        inventory.addItem("9");
        inventory.addItem("10");
        inventory.addItem("11");
        inventory.addItem("12");
        inventory.addItem("13");
        assertTrue(inventory.addItem("14"));
        assertTrue(inventory.addItem("15"));
        assertFalse(inventory.addItem("16"));
        assertTrue(inventory.getItems().contains("15"));
        assertFalse(inventory.getItems().contains("16"));

    }

}
