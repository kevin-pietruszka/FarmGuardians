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
import main.ui.controller.HarvestController;
import src.main.Plot;
import src.main.ui.ScreenMaker;
import src.main.ui.controller.FarmController;

import java.util.ArrayList;

public class HarvestScreen extends ScreenMaker {

    public HarvestScreen(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() {

        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        ArrayList<Plot> plots = FarmController.getPlots();

        Text title = new Text("Harvest Plots or Remove Dead Crops");
        title.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");

        Button returnToFarm = new Button("Return to farm");
        returnToFarm.setOnAction(e -> HarvestController.returnToGame());

        VBox screen = new VBox(
                title,
                getHarvestPlotRow(plots.get(0), 1),
                getHarvestPlotRow(plots.get(1), 2),
                getHarvestPlotRow(plots.get(2), 3),
                getHarvestPlotRow(plots.get(3), 4),
                getHarvestPlotRow(plots.get(4), 5),
                getHarvestPlotRow(plots.get(5), 6),
                getHarvestPlotRow(plots.get(6), 7),
                getHarvestPlotRow(plots.get(7), 8),
                getHarvestPlotRow(plots.get(8), 9),
                getHarvestPlotRow(plots.get(9), 10),
                getHarvestPlotRow(plots.get(10), 11),
                getHarvestPlotRow(plots.get(11), 12),
                getHarvestPlotRow(plots.get(12), 13),
                getHarvestPlotRow(plots.get(13), 14),
                getHarvestPlotRow(plots.get(14), 15),
                getHarvestPlotRow(plots.get(15), 16),
                returnToFarm
        );

        screen.setSpacing(10);
        screen.setAlignment(Pos.CENTER);

        return new Scene(screen, screenSize.getWidth(), screenSize.getHeight());
    }

    private HBox getHarvestPlotRow(Plot p, int n) {

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

        out.getChildren().add(new Text("Harvest plot " + n + "?: "));

        if (p.getCrop() == null) {

            out.getChildren().add(new Text("No plants :("));

        } else if (p.getStageOfGrowth() == 2) {

            Button b = new Button("Harvest plot " + n);
            b.setOnAction(e ->
                    HarvestController.harvest(n));
            out.getChildren().add(b);

        } else if (p.getStageOfGrowth() == -1) {
            Button b = new Button("Remove dead plot");
            b.setOnAction(e ->
                    HarvestController.harvest(n));
            out.getChildren().add(b);
        } else {

            out.getChildren().add(new Text("Not able to be harvested."));

        }

        out.setAlignment(Pos.CENTER);

        return out;

    }
}
