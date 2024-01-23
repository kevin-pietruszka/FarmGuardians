package src.main.ui.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;
import src.main.GameConfigurator;

import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;
import src.main.Player;
import src.main.ui.ScreenMaker;
import src.main.Plot;
import src.main.ui.controller.FarmController;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Class that represents the Farmscreen
 *
 * @author Kevin Pietruszka
 * @version 2.0
 */
public class FarmScreen extends ScreenMaker {

    private HashMap<VBox, Plot> plotMap;

    public FarmScreen(Stage stage) {
        super(stage);
    }

    @Override
    public Scene getScene() {

        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        // Get the current player that should be displayed
        Player currPlayer = GameConfigurator.getPlayer();

        // Grabs and sets the display for players money
        Text money = new Text();
        money.setText("Money: " + Double.toString(currPlayer.getMoney()));
        money.setTextAlignment(TextAlignment.LEFT);
        money.setStyle("-fx-font: 36 arial;");

        // Inventory button
        Button inventoryButton = new Button("Inventory");
        inventoryButton.setOnAction(e ->
                GameConfigurator.changeToScreen(GameConfigurator.ScreenName.INVENTORY_SCREEN));

        // Market button
        Button marketButton = new Button("Market");
        marketButton.setOnAction(e ->
                GameConfigurator.changeToScreen(GameConfigurator.ScreenName.MARKET_SCREEN));

        // Harvest button
        Button harvestButton = new Button("Harvest");
        harvestButton.setOnAction(e ->
                GameConfigurator.changeToScreen(GameConfigurator.ScreenName.HARVEST_SCREEN));

        // Water button
        Button waterButton = new Button("Water");
        waterButton.setOnAction(e ->
                GameConfigurator.changeToScreen(GameConfigurator.ScreenName.WATER_SCREEN));

        // Fertilize Button
        Button fertilizeButton = new Button("Fertilize");
        fertilizeButton.setOnAction(e ->
                GameConfigurator.changeToScreen(GameConfigurator.ScreenName.FERTILIZE_SCREEN));
        // Pesticide Button
        Button pesticideButton = new Button("Pesticide");
        pesticideButton.setOnAction(e ->
                GameConfigurator.changeToScreen(GameConfigurator.ScreenName.PESTICIDE_SCREEN));
        // Plant Button
        Button plantButton = new Button("Plant Seeds");
        plantButton.setOnAction(e ->
                GameConfigurator.changeToScreen(GameConfigurator.ScreenName.PLANT_SCREEN));

        // Time button and label
        Label timeLabel = new Label("Time: " + GameConfigurator.getTime() + " day"
                + (GameConfigurator.getTime() > 1 ? "s" : ""));
        timeLabel.setTextAlignment(TextAlignment.RIGHT);
        timeLabel.setStyle("-fx-font: 36 arial;");
        Button timeButton = new Button("Progress Time");
        timeButton.setOnAction(e -> FarmController.advanceTime(timeLabel));
        Text worker = new Text();
        if (currPlayer.getWorker() == null) {
            worker.setText("Worker Not Hired");
        } else {
            worker.setText("Worker Hired: $" + currPlayer.getWorker().getWage() + " / day");
        }
        worker.setTextAlignment(TextAlignment.CENTER);
        worker.setStyle("-fx-font: 36 arial;");
        // Top row container
        HBox information = new HBox(money, timeLabel, worker);
        information.setSpacing(1 * screenSize.getWidth() / 10);
        information.setPadding(new Insets(15));

        // Lays out the plot grid
        ArrayList<Plot> plots = FarmController.getPlots();

        VBox plot0 = getPlotDrawing(plots.get(0));
        VBox plot1 = getPlotDrawing(plots.get(1));
        VBox plot2 = getPlotDrawing(plots.get(2));
        VBox plot3 = getPlotDrawing(plots.get(3));
        VBox plot4 = getPlotDrawing(plots.get(4));
        VBox plot5 = getPlotDrawing(plots.get(5));
        VBox plot6 = getPlotDrawing(plots.get(6));
        VBox plot7 = getPlotDrawing(plots.get(7));
        VBox plot8 = getPlotDrawing(plots.get(8));
        VBox plot9 = getPlotDrawing(plots.get(9));
        VBox plot10 = getPlotDrawing(plots.get(10));
        VBox plot11 = getPlotDrawing(plots.get(11));
        VBox plot12 = getPlotDrawing(plots.get(12));
        VBox plot13 = getPlotDrawing(plots.get(13));
        VBox plot14 = getPlotDrawing(plots.get(14));
        VBox plot15 = getPlotDrawing(plots.get(15));
        plotMap = new HashMap<>();
        plotMap.put(plot0, plots.get(0));
        plotMap.put(plot1, plots.get(1));
        plotMap.put(plot2, plots.get(2));
        plotMap.put(plot3, plots.get(3));
        plotMap.put(plot4, plots.get(4));
        plotMap.put(plot5, plots.get(5));
        plotMap.put(plot6, plots.get(6));
        plotMap.put(plot7, plots.get(7));
        plotMap.put(plot8, plots.get(8));
        plotMap.put(plot9, plots.get(9));
        plotMap.put(plot10, plots.get(10));
        plotMap.put(plot11, plots.get(11));
        plotMap.put(plot12, plots.get(12));
        plotMap.put(plot13, plots.get(13));
        plotMap.put(plot14, plots.get(14));
        plotMap.put(plot15, plots.get(15));

        HBox row1 = new HBox(
                plot0,
                plot1,
                plot2,
                plot3
        );
        row1.setSpacing(100);
        row1.setAlignment(Pos.CENTER);

        HBox row2 = new HBox(
                plot4,
                plot5,
                plot6,
                plot7
        );
        row2.setSpacing(100);
        row2.setAlignment(Pos.CENTER);

        HBox row3 = new HBox(
                plot8,
                plot9,
                plot10,
                plot11
        );
        row3.setSpacing(100);
        row3.setAlignment(Pos.CENTER);

        HBox row4 = new HBox(
                plot12,
                plot13,
                plot14,
                plot15
        );
        row4.setSpacing(100);
        row4.setAlignment(Pos.CENTER);

        VBox farmGrid = new VBox(row1, row2, row3, row4);
        farmGrid.setSpacing(15);
        farmGrid.setAlignment(Pos.CENTER);

        HBox buttons = new HBox(
                inventoryButton,
                marketButton,
                waterButton,
                fertilizeButton,
                pesticideButton,
                harvestButton,
                plantButton,
                timeButton
        );

        buttons.setSpacing(25);
        buttons.setPadding(new Insets(15));
        buttons.setAlignment(Pos.CENTER);

        VBox farmScreen = new VBox(
                information,
                buttons,
                farmGrid
        );

        return new Scene(farmScreen, screenSize.getWidth(), screenSize.getHeight());

    }

    private VBox getPlotDrawing(Plot p) {

        if (p == null) {

            Rectangle plot = new Rectangle(125, 150);
            Text buy = new Text("Buy plot in Market");

            return new VBox(buy, plot);
        }


        Text cropName;
        Text fertilizerLevel;
        if (p.getCrop() == null) {
            cropName = new Text("Crop: None");
            fertilizerLevel = new Text("Fertilize Level: N/A");
        } else {
            cropName = new Text("Crop: " + p.getCrop().getType());
            fertilizerLevel = new Text("Fertilizer Level: " + p.getCrop().getFertilizeLevel());
        }

        Rectangle plot = new Rectangle(125, 150);
        plot.setFill(p.getColor());
        Rectangle waterLevel = new Rectangle(150, 20);
        waterLevel.setWidth(p.getWaterLevelWidth());
        waterLevel.setFill(p.getWaterLevelColor());
        Text level = new Text(p.getWaterLevel());
        HBox water = new HBox(waterLevel, level);
        return new VBox(cropName, fertilizerLevel, plot, water);
    }

    @Override
    public void update() {

        if (plotMap != null) {

            for (VBox plotBox : plotMap.keySet()) {

                if (plotMap.get(plotBox) != null) {

                    ((Rectangle) plotBox.getChildren().get(2)).setFill(plotMap.get(plotBox).getColor());
                }

            }

        }

    }

}
