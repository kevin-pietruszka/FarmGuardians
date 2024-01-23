package src.main.ui.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import src.main.GameConfigurator;
import src.main.Player;


public class ConfigurationController {

    @FXML
    private TextField name;

    @FXML
    private ComboBox<String> difficulty;

    @FXML
    private ComboBox<String> seed;

    @FXML
    private ComboBox<String> season;


    @FXML
    public void farmScene(ActionEvent event)  {
        if (name.getText().equals("") || difficulty.getValue() == null || seed.getValue() == null
                || season.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("One or more fields are empty");
            alert.show();
        } else {
            Player player = GameConfigurator.getPlayer();
            player.setName(name.getText());
            GameConfigurator.setDifficulty(difficulty.getValue());
            player.setSeason(season.getValue());
            player.setStartingSeed(seed.getValue());
            FarmController.createInitialPlots();
            GameConfigurator.changeToScreen(GameConfigurator.ScreenName.FARM_SCREEN);
        }

    }


}
