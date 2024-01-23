package src.main.ui.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import main.FarmWorker;
import main.PlotOfLand;
import src.main.GameConfigurator;
import src.main.Player;
import src.main.MarketItems;
import src.main.MarketPlace;

import java.util.Optional;

public class MarketController {

    public void toFarm() {
        src.main.GameConfigurator.changeToScreen(src.main.GameConfigurator.ScreenName.FARM_SCREEN);
    }

    public double getDifficultyVariance() {
        String difficulty = src.main.GameConfigurator.getDifficulty();
        if (difficulty.equals("Egg")) {
            return 0.75;
        } else if (difficulty.equals("Chicken")) {
            return 1.5;
        } else {
            return 2;
        }
    }

    public double getPrice(String item, MarketPlace market) {
        double difficultyVariance = getDifficultyVariance();
        double price = 0.1 + difficultyVariance + market.findItemPrice(item + " Seeds");
        if (item.contains("Seeds")) {
            // seeds depreciate in value
            price = -0.05 + -(difficultyVariance * 0.5) + market.findItemPrice(item);
        } else if (item.equals("Pesticide") || item.equals("Fertilizer")
                || item.equals("Tractor") || item.equals("Irrigation")) {
            price = market.findItemPrice(item);
        }
        return Math.round(price * 100.0) / 100.0;
    }


    public void sell(Player currPlayer, String selectedItem, MarketPlace market) {
        if (selectedItem != null) {
            double price = getPrice(selectedItem, market);

            // inform player of sell price
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Sale price is $" + price);
            Optional<ButtonType> result = alert.showAndWait();

            // if they accept price, make sale
            if (result.isPresent() && result.get() == ButtonType.OK) {
                currPlayer.setMoney(Math.round((currPlayer.getMoney() + price) * 100.0) / 100.0);
                currPlayer.getInventory().removeItem(selectedItem);
                src.main.GameConfigurator.changeToScreen(GameConfigurator.ScreenName.MARKET_SCREEN);
            }
        }
    }

    public void buy(Player currPlayer, MarketItems selectedItem) {
        double price = selectedItem.getPrice();

        // checks player's money balance and inventory capacity
        if (currPlayer.getMoney() < price) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(currPlayer.getName()
                    + " does not have enough money to purchase item");
            alert.show();
        } else if (currPlayer.getInventory().getItems().size() + 1 > 20) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(currPlayer.getName()
                    + " does not have enough inventory space to purchase item");
            alert.show();
        } else if (selectedItem instanceof PlotOfLand){

            int result = src.main.ui.controller.FarmController.addPlot();

            if (result == 1) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("No more farm space to grow number of plots. Please wait for DLC after COVID ends");
                alert.show();
            } else {

                currPlayer.setMoney(Math.round((currPlayer.getMoney() - price) * 100.0) / 100.0);
            }

        } else {
            currPlayer.setMoney(Math.round((currPlayer.getMoney() - price) * 100.0) / 100.0);
            currPlayer.getInventory().addItem(selectedItem.getType());
        }
        src.main.GameConfigurator.changeToScreen(GameConfigurator.ScreenName.MARKET_SCREEN);
    }

    public void hireWorker(Player currPlayer, int skill, double wage) {
        currPlayer.setWorker(new FarmWorker(skill, wage));
    }
}
