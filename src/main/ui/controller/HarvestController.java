package main.ui.controller;


import javafx.scene.control.Alert;
import src.main.ui.controller.FarmController;

import java.util.Random;

public class HarvestController {

    private static int harvestCount;
    private static int harvestLimit;

    public static int getHarvestCount() {
        return harvestCount;
    }

    public static void resetHarvestCount() {
        harvestCount = 0;
    }

    public static int getHarvestLimit() {
        return harvestLimit;
    }

    public static void returnToGame() {

        src.main.GameConfigurator.changeToScreen(src.main.GameConfigurator.ScreenName.FARM_SCREEN);

    }

    public static void harvest(int plotNumber) {

        src.main.Plot plot = FarmController.getPlots().get(plotNumber - 1);
        src.main.Player p = src.main.GameConfigurator.getPlayer();
        // no tractor: 1 plot
        // with tractor: 5 plots
        harvestLimit = p.getInventory().findTractor() ? 5 : 1;

        int harvestModifier = 1;
        if (plot.getCrop().getFertilizeLevel() > 0) {

            Random r = new Random();

            harvestModifier = r.nextInt(4);

        }
        if (harvestCount >= harvestLimit) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Your arms are tired of harvesting. "
                    + "Buy a tractor or wait until tomorrow to harvest more");
            alert.show();
        } else {
            if (plot.getStageOfGrowth() == 2) {

                for (int i = 0; i < harvestModifier; i++) {
                    p.harvest(plot.getCrop());
                }

                plot.setCrop(null);
                plot.setStageOfGrowth(0);
                plot.resetLastWatered();

            } else if (plot.getStageOfGrowth() == -1) {
                plot.setCrop(null);
                plot.setStageOfGrowth(0);
                plot.resetLastWatered();
            }
            harvestCount++;
        }
        returnToGame();

    }
}
