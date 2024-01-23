package src.main.ui.view;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import src.main.GameConfigurator;
import src.main.Player;
import src.main.ui.ScreenMaker;

public class LossView extends ScreenMaker {

    public LossView(Stage stage) {

        super(stage);

    }

    @Override
    public Scene getScene() {

        Label label = new Label("Sorry " + GameConfigurator.getPlayer().getName()
                + ", You Lost! :(");

        Button quitButton = new Button("Quit");
        quitButton.setOnAction(e -> System.exit(0));

        Button replayButton = new Button("Replay");
        replayButton.setOnAction(e -> {
            GameConfigurator.changeToScreen(GameConfigurator.ScreenName.WELCOME_SCREEN);
            GameConfigurator.setPlayer(new Player());
        });

        HBox buttons = new HBox(quitButton, replayButton);
        VBox vbox = new VBox(label, buttons);

        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        return new Scene(vbox, screenSize.getWidth(), screenSize.getHeight());

    }

}
