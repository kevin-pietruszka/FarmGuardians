package src.main.ui.view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import src.main.GameConfigurator;
import src.main.Player;
import src.main.ui.ScreenMaker;

public class WinView extends ScreenMaker {

    public WinView(Stage stage) {

        super(stage);

    }

    @Override
    public Scene getScene() {

        Label label = new Label("Congrats " + GameConfigurator.getPlayer().getName()
                + ", You Won! :)");

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> System.exit(0));

        Button bragButton = new Button("Brag to friends, onlookers, and fans");
        bragButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("YOU WON!!!!!!!!!");
            alert.show();
        });

        Button replayButton = new Button("Replay");
        replayButton.setOnAction(e -> {
            GameConfigurator.changeToScreen(GameConfigurator.ScreenName.WELCOME_SCREEN);
            GameConfigurator.setPlayer(new Player());
        });

        HBox buttons = new HBox(quitButton, bragButton, replayButton);
        VBox vbox = new VBox(label, buttons);

        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        return new Scene(vbox, screenSize.getWidth(), screenSize.getHeight());

    }

}
