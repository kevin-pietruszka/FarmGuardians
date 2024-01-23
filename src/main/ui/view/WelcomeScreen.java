package src.main.ui.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import src.main.GameConfigurator;

public class WelcomeScreen extends src.main.ui.ScreenMaker {

    public WelcomeScreen(Stage stage) {

        super(stage);

    }

    @Override
    public Scene getScene() {

        Text title = new Text("FarmGuardians");
        Button startGameButton = new Button("Start Game");

        startGameButton.setOnAction(e -> startGame());

        VBox vbox = new VBox(title, startGameButton);
        vbox.setSpacing(25);
        vbox.setAlignment(Pos.CENTER);

        return new Scene(vbox, 400, 300);

    }

    /**
     * Transitions the game state to the configuration screen when the start game button is pressed
     */
    public void startGame() {

        // Actually change the screen
        GameConfigurator.changeToScreen(GameConfigurator.ScreenName.CONFIGURATION_SCREEN);

    }

}
