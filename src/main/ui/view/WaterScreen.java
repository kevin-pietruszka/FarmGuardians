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
import src.main.Plot;
import src.main.ui.ScreenMaker;
import src.main.ui.controller.FarmController;

import java.util.ArrayList;

public class WaterScreen extends ScreenMaker {

    public WaterScreen(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() {

        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        ArrayList<Plot> plots = FarmController.getPlots();

        Text title = new Text("Don't forget to water your plots!");
        title.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");

        Button returnToFarm = new Button("Return to farm");
        returnToFarm.setOnAction(e -> FarmController.returnToGame());

        VBox screen = new VBox(
                title,
                getWaterPlotRow(plots.get(0), 1),
                getWaterPlotRow(plots.get(1), 2),
                getWaterPlotRow(plots.get(2), 3),
                getWaterPlotRow(plots.get(3), 4),
                getWaterPlotRow(plots.get(4), 5),
                getWaterPlotRow(plots.get(5), 6),
                getWaterPlotRow(plots.get(6), 7),
                getWaterPlotRow(plots.get(7), 8),
                getWaterPlotRow(plots.get(8), 9),
                getWaterPlotRow(plots.get(9), 10),
                getWaterPlotRow(plots.get(10), 11),
                getWaterPlotRow(plots.get(11), 12),
                getWaterPlotRow(plots.get(12), 13),
                getWaterPlotRow(plots.get(13), 14),
                getWaterPlotRow(plots.get(14), 15),
                getWaterPlotRow(plots.get(15), 16),
                returnToFarm
        );

        screen.setSpacing(10);
        screen.setAlignment(Pos.CENTER);

        return new Scene(screen, screenSize.getWidth(), screenSize.getHeight());
    }

    private HBox getWaterPlotRow(Plot p, int n) {

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

        out.getChildren().add(new Text("Water plot " + n + "?: "));

        if (p.getCrop() == null) {

            out.getChildren().add(new Text("No plants"));

        } else if (p.getStageOfGrowth() == -1) {
            out.getChildren().add(new Text("Dead crop :("));
        } else {

            Button b = new Button("Water plot " + n);
            b.setOnAction(e -> FarmController.water(n));
            out.getChildren().add(b);
        }

        out.setAlignment(Pos.CENTER);

        return out;

    }
}