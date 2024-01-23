package tests.ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.framework.junit.ApplicationTest;
import src.main.Player;
import src.main.GameConfigurator;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import static org.junit.Assert.assertEquals;


public class TestConfigurationScreen extends ApplicationTest {

    @Override
    public void start(Stage stage) throws IOException {
        Player player = new Player();
        GameConfigurator.setPlayer(player);
        URL url = new File("src/main/ui/view/ConfigurationScreen.fxml").toURI().toURL();
        stage.setScene(new Scene(FXMLLoader.load(url)));
        stage.show();
        stage.toFront();
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void selectSeason() {
        ComboBox<String> box = (ComboBox<String>) GuiTest.find("#season");

        clickOn("#season").clickOn("Spring");
        assertEquals(box.getValue(), "Spring");

        clickOn("#season").clickOn("Summer");
        assertEquals(box.getValue(), "Summer");

        clickOn("#season").clickOn("Fall");
        assertEquals(box.getValue(), "Fall");

        clickOn("#season").clickOn("Winter");
        assertEquals(box.getValue(), "Winter");
    }

    @Test
    public void selectSeedType() {
        ComboBox<String> box = (ComboBox<String>) GuiTest.find("#seed");

        clickOn("#seed").clickOn("Corn Seeds");
        assertEquals(box.getValue(), "Corn Seeds");

        clickOn("#seed").clickOn("Pumpkin Seeds");
        assertEquals(box.getValue(), "Pumpkin Seeds");

        clickOn("#seed").clickOn("Potato Seeds");
        assertEquals(box.getValue(), "Potato Seeds");

    }

    @Test
    public void testPlayer() {

        clickOn("#name").write("Player1");
        clickOn("#difficulty").clickOn("Egg");
        clickOn("#seed").clickOn("Corn Seeds");
        clickOn("#season").clickOn("Spring");
        clickOn("#submit");

        Player person = GameConfigurator.getPlayer();
        assertEquals(person.getName(), "Player1");
        assertEquals(person.getSeason(), "Spring");
        assertEquals(person.getMoney(), 100.0, .01);

    }

}