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

import java.util.ArrayList;

public class FertilizeView extends src.main.ui.ScreenMaker {

    public FertilizeView(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() {

        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();


        ArrayList<src.main.Plot> plots = src.main.ui.controller.FarmController.getPlots();

        Text title = new Text("Fertilize your plots!");
        title.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");

        Button returnToFarm = new Button("Return to farm");
        returnToFarm.setOnAction(e -> src.main.ui.controller.FarmController.returnToGame());

        VBox screen = new VBox(
                title,
                gePlotRow(plots.get(0), 1),
                gePlotRow(plots.get(1), 2),
                gePlotRow(plots.get(2), 3),
                gePlotRow(plots.get(3), 4),
                gePlotRow(plots.get(4), 5),
                gePlotRow(plots.get(5), 6),
                gePlotRow(plots.get(6), 7),
                gePlotRow(plots.get(7), 8),
                gePlotRow(plots.get(8), 9),
                gePlotRow(plots.get(9), 10),
                gePlotRow(plots.get(10), 11),
                gePlotRow(plots.get(11), 12),
                gePlotRow(plots.get(12), 13),
                gePlotRow(plots.get(13), 14),
                gePlotRow(plots.get(14), 15),
                gePlotRow(plots.get(15), 16),
                returnToFarm
        );

        screen.setSpacing(10);
        screen.setAlignment(Pos.CENTER);

        return new Scene(screen, screenSize.getWidth(), screenSize.getHeight());
    }

    private HBox gePlotRow(src.main.Plot p, int n) {

        if (p == null) {
            HBox out =  new HBox(
                    new Text("Buy plot " + n + " from the market")
            );
            out.setAlignment(Pos.CENTER);
            return out;
        }

        HBox out = new HBox();

        out.getChildren().add(new Text("Fertilize plot " + n + "?: "));

        if (p.getCrop() == null) {
            out.getChildren().add(new Text("No plants"));
        } else {
            Button b = new Button("Fertilize plot " + n);
            b.setOnAction(e -> src.main.ui.controller.FarmController.fertilize(n));
            out.getChildren().add(b);
        }

        out.setAlignment(Pos.CENTER);

        return out;

    }
}
