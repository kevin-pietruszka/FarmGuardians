package src.main.ui.view;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import src.main.ui.controller.PlantController;
import src.main.Crop;

public class PlantOptionsView extends src.main.ui.ScreenMaker {
    public PlantOptionsView(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() {
        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        src.main.Inventory<String> inventory = PlantController.getinventory();


        Text title = new Text("Which seeds would you like to plant?");
        title.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");

        Button returnToFarm = new Button("Return to farm");
        returnToFarm.setOnAction(e -> PlantController.returnToGame());


        VBox screen = new VBox(title);

        for (String item : inventory.getItems()) {
            if (item.contains("Seeds")) {
                Button plantOption = new Button("Plant " + item);
                String crop;
                if (item.contains("Pumpkin")) {
                    crop = "Pumpkin";
                } else if (item.contains("Corn")) {
                    crop = "Corn";
                } else {
                    crop = "Potato";
                }

                plantOption.setOnAction(e -> {
                    inventory.removeItem(item);
                    PlantController.plant(new Crop(crop));
                });

                screen.getChildren().add(plantOption);
            }
        }

        screen.getChildren().add(returnToFarm);

        screen.setSpacing(10);
        screen.setAlignment(Pos.CENTER);

        return new Scene(screen, screenSize.getWidth(), screenSize.getHeight());

    }
}
