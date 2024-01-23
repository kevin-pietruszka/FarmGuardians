package src.main.ui.controller;


import src.main.GameConfigurator;
import src.main.Plot;

public class PlantController {

    private static int plotNumber;

    public static void returnToGame() {

        src.main.GameConfigurator.changeToScreen(GameConfigurator.ScreenName.FARM_SCREEN);

    }

    public static src.main.Inventory<String> getinventory() {
        return src.main.GameConfigurator.getPlayer().getInventory();
    }

    public static void switchToPlantOptions(int p) {
        plotNumber = p;
        src.main.GameConfigurator.changeToScreen(GameConfigurator.ScreenName.PLANT_OPTIONS_SCREEN);
    }

    public static void plant(src.main.Crop crop) {
        src.main.ui.controller.FarmController.getPlots().set(plotNumber - 1, new Plot(0, crop));
        returnToGame();
    }
}
