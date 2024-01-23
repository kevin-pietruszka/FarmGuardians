package tests.ui;

import org.junit.Before;
import org.junit.Test;
import src.main.ui.view.WelcomeScreen;

public class TestWelcomeScreen {

    private WelcomeScreen welcomeScreen;

    @Before
    public void setup() {

        welcomeScreen = new WelcomeScreen(null);

    }

    /**
     * Tests the constructor for no errors or exceptions.
     */
    @Test
    public void testConstructor() {

        new WelcomeScreen(null);

    }

    /**
     * Tests that WelcomeScreen.startGame() does not error.
     */
    @Test
    public void testStartGame() {

        welcomeScreen.startGame();

    }

}
