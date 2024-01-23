package src.main.ui.controller;

import src.main.GameConfigurator;

import java.util.List;

public class InventoryController {

    public void returnToGame() {

        GameConfigurator.changeToScreen(GameConfigurator.ScreenName.FARM_SCREEN);

    }

    public List<String> getInventoryItems() {

        List<String> inventory = GameConfigurator.getPlayer().getInventory().getItems();
        return inventory;

    }

}
