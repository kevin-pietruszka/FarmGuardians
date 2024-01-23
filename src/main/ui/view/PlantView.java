package src.main.ui.view;

import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import src.main.ui.controller.PlantController;
import src.main.ui.ScreenMaker;

import java.util.ArrayList;

public class PlantView extends ScreenMaker {
    public PlantView(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() {
        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        ArrayList<src.main.Plot> plots = src.main.ui.controller.FarmController.getPlots();

        Text title = new Text("Plant new seeds");
        title.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");

        Button returnToFarm = new Button("Return to farm");
        returnToFarm.setOnAction(e -> PlantController.returnToGame());

        VBox screen = new VBox(
                title,
                getPlantingRow(plots.get(0), 1),
                getPlantingRow(plots.get(1), 2),
                getPlantingRow(plots.get(2), 3),
                getPlantingRow(plots.get(3), 4),
                getPlantingRow(plots.get(4), 5),
                getPlantingRow(plots.get(5), 6),
                getPlantingRow(plots.get(6), 7),
                getPlantingRow(plots.get(7), 8),
                getPlantingRow(plots.get(8), 9),
                getPlantingRow(plots.get(9), 10),
                getPlantingRow(plots.get(10), 11),
                getPlantingRow(plots.get(11), 12),
                getPlantingRow(plots.get(12), 13),
                getPlantingRow(plots.get(13), 14),
                getPlantingRow(plots.get(14), 15),
                getPlantingRow(plots.get(15), 16),
                returnToFarm
        );

        screen.setSpacing(10);
        screen.setAlignment(Pos.CENTER);

        return new Scene(screen, screenSize.getWidth(), screenSize.getHeight());
    }

    private HBox getPlantingRow(src.main.Plot p, int n) {

        if (p == null) {

            if (p == null) {

                HBox out =  new HBox(
                        new Text("Buy plot " + n + " from the market")
                );
                out.setAlignment(Pos.CENTER);
                return out;

            }

        }

        HBox out = new HBox();

        out.getChildren().add(new Text("Plant seeds on plot " + n + "?: "));

        if (p.getCrop() == null) {
            Button b = new Button("Plant plot " + n);
            b.setOnAction(e ->
                    PlantController.switchToPlantOptions(n)
            );
            out.getChildren().add(b);
        } else {
            out.getChildren().add(new Text("There is already a plant in this plot, Crop: "
                    + p.getCrop().getType()));
        }

        out.setAlignment(Pos.CENTER);

        return out;
    }
}
