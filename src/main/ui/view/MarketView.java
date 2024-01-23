package src.main.ui.view;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Screen;
import javafx.stage.Stage;
import main.PlotOfLand;
import src.main.Fertilizer;
import src.main.MarketItems;
import src.main.MarketPlace;
import src.main.Player;
import src.main.Seeds;
import src.main.Pesticide;
import src.main.Tractor;
import src.main.Irrigation;
import src.main.ui.controller.MarketController;

import java.util.List;

public class MarketView extends src.main.ui.ScreenMaker {

    private static MarketController controller;

    public MarketView(Stage stage) {
        super(stage);
        controller = new MarketController();
    }

    @Override
    public Scene getScene() {

        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        // Get the current player that should be displayed
        Player currPlayer = src.main.GameConfigurator.getPlayer();

        // Create MarketPlace
        MarketPlace market = new MarketPlace();
        // basePrice for every seed is 10 cents + difficulty variance + seed type cost
        double difficulty = controller.getDifficultyVariance();
        double basePrice = 0.10 + difficulty;
        market.addItem(new Seeds(basePrice, "Corn Seeds"));
        market.addItem(new Seeds(basePrice + .05, "Potato Seeds"));
        market.addItem(new Seeds(basePrice + .10, "Pumpkin Seeds"));
        market.addItem(new Pesticide());
        market.addItem(new Fertilizer());
        market.getItem(3).setPrice(difficulty + 1.5);
        market.getItem(4).setPrice(difficulty + 3.0);
        market.addItem(new Tractor());
        market.addItem(new Irrigation());
        market.addItem(new PlotOfLand());
        double plotPrice = Math.pow(2.5, src.main.ui.controller.FarmController.getNumberOfPlots() - 11);
        market.getItem(7).setPrice(plotPrice);

        // Grabs and sets the display for players money
        Text money = new Text();
        money.setText("Money: " + currPlayer.getMoney());
        money.setTextAlignment(TextAlignment.LEFT);
        money.setStyle("-fx-font: 36 arial;");


        // Return to Farm Button
        Button farmButton = new Button("Return to Farm");
        farmButton.setOnAction(e -> controller.toFarm());


        // Top row container
        VBox information = new VBox(money, farmButton);
        information.setSpacing(25);
        information.setPadding(new Insets(15));

        // Display Player Inventory and Sell option
        ObservableList<String> inventory = FXCollections.observableArrayList();
        List<String> items = currPlayer.getInventory().getItems();
        for (int i = 0; i < items.size(); i++) {
            inventory.add(items.get(i));
        }
        Text inventoryTitle = new Text(currPlayer.getName() + "'s Inventory");
        inventoryTitle.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");
        ListView<String> inventoryList = new ListView<>(inventory);

        Button sell = new Button("Sell Item");
        sell.setOnAction(e -> controller.sell(currPlayer,
                inventoryList.getSelectionModel().getSelectedItem(), market));

        VBox inventoryView = new VBox(inventoryTitle, inventoryList, sell);

        // Display Market Place and Buy option
        ObservableList<MarketItems> marketItems = FXCollections.observableArrayList();
        for (int i = 0; i < market.getItems().size(); i++) {
            marketItems.add(market.getItem(i));
        }
        Text marketTitle = new Text("Market Items for Sale");
        marketTitle.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");
        ListView<MarketItems> marketList = new ListView<>(marketItems);

        Button buy = new Button("Buy Item");
        Button hireLow = new Button("Hire Low Skilled Farm Worker: ");
        Button hireHigh = new Button("Hire High Skilled Farm Worker");
        buy.setOnAction(e -> controller.buy(currPlayer,
                marketList.getSelectionModel().getSelectedItem()));
        hireHigh.setOnAction(e -> controller.hireWorker(currPlayer, 1, 10 + difficulty));
        hireLow.setOnAction(e -> controller.hireWorker(currPlayer, 0, 5 + difficulty));
        VBox marketView = new VBox(marketTitle, marketList, buy, hireLow, hireHigh);

        // Puts the screen together
        HBox lists = new HBox(inventoryView, marketView);
        lists.setSpacing(50);
        lists.setAlignment(Pos.CENTER);
        VBox marketScreen = new VBox(information, lists);
        marketScreen.setSpacing(50);

        return new Scene(marketScreen, screenSize.getWidth(), screenSize.getHeight());



    }

}