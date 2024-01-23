package src.main.ui.view;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ConfigurationScreen extends src.main.ui.ScreenMaker {


    public ConfigurationScreen(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() {

        try {

            URL url = new File("src/main/ui/view/ConfigurationScreen.fxml").toURI().toURL();
            return new Scene(FXMLLoader.load(url));

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;

    }

}
