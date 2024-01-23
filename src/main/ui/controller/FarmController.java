package src.main.ui.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import main.ui.controller.HarvestController;
import src.main.Crop;
import src.main.Player;
import src.main.Plot;
import src.main.GameConfigurator;

import java.util.ArrayList;
import java.util.Random;

public class FarmController {

    private static ArrayList<Plot> plots;
    private static Random rand = new Random();
    private static int waterCount = 0;
    private static int waterLimit;
    private static int numberOfPlots = 12;

    // Constants
    private static final int NUM_INITIAL_PLOTS = 12;

    public static int getNumberOfPlots() {
        return numberOfPlots;
    }

    public static int getWaterCount() {
        return waterCount;
    }

    public static void resetWaterCount() {
        waterCount = 0;
    }

    public static int getWaterLimit() {
        return waterLimit;
    }

    public static ArrayList<Plot> getPlots() {
        return plots;
    }

    public static void createInitialPlots() {

        plots = new ArrayList<Plot>(36);
        Random rand = new Random();

        for (int i = 0; i < NUM_INITIAL_PLOTS; i++) {

            int stage = rand.nextInt(3);
            int s = rand.nextInt(3);

            Crop crop;
            if (s == 0) {
                crop = null;
            } else if (s == 1) {
                crop = new Crop("Corn");
            } else if (s == 2) {
                crop = new Crop("Pumpkin");
            } else {
                crop = new Crop("Potato");
            }

            plots.add(new Plot(stage, crop));
        }

        for (int j = 0; j < 4; j++) {
            plots.add(null);
        }
    }

    public static int addPlot() {

        if (numberOfPlots < 16) {

            plots.add(numberOfPlots, new Plot(0, null));
            numberOfPlots++;
            return 0;

        }
        return 1;
    }

    public static String advanceTime(Label timeLabel) {

        if (plots != null) {

            for (Plot p : plots) {

                if (p != null)
                    p.grow();

            }

        }
        Player currPlayer = GameConfigurator.getPlayer();
        if (currPlayer.getWorker() != null
                && currPlayer.getMoney() > currPlayer.getWorker().getWage()) {
            currPlayer.setMoney(Math.round((currPlayer.getMoney()
                    - currPlayer.getWorker().getWage()) * 100.0) / 100.0);
            int harvested = 0;
            int plot = 0;
            if (currPlayer.getWorker().getSkill() == 0) {
                while (plot < plots.size() && harvested < 2) {
                    if (plots.get(plot) != null && plots.get(plot).getStageOfGrowth() == 2) {
                        HarvestController.harvest(plot + 1);
                        harvested++;
                    }
                    plot++;
                }
            } else {
                while (plot < plots.size() && harvested < 4) {
                    if (plots.get(plot) != null && plots.get(plot).getStageOfGrowth() == 2) {
                        HarvestController.harvest(plot + 1);
                        harvested++;
                    }
                    plot++;
                }
            }
        } else if (currPlayer.getWorker() != null
                && currPlayer.getMoney() < currPlayer.getWorker().getWage()) {
            currPlayer.setWorker(null);
        }

        GameConfigurator.advanceTime();

        // Handle random events
        float r = rand.nextFloat();
        float eventCutoff = 0.35f;
        float rainCutoff = GameConfigurator.getDifficulty().equals("Egg") ? 0.5f
                : (GameConfigurator.getDifficulty().equals("Chicken") ? 0.3f : 0.1f);
        float droughtCutoff = GameConfigurator.getDifficulty().equals("Egg") ? 0.1f
                : (GameConfigurator.getDifficulty().equals("Chicken") ? 0.3f : 0.5f);
        float locustCutoff = GameConfigurator.getDifficulty().equals("Egg") ? 0.1f
                : (GameConfigurator.getDifficulty().equals("Chicken") ? 0.3f : 0.5f);

        if (r < eventCutoff) {

            if (rand.nextFloat() < rainCutoff) { // Rain

                r = rand.nextInt(3) + 1;
                for (int w = 0; w < r; w++) {
                    for (int i = 0; i < FarmController.getPlots().size(); i++) {
                        if (FarmController.getPlots().get(i) != null)
                            FarmController.water(i + 1);
                    }
                }

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Your plants were watered " + (int) r + " times!");
                a.show();

            } else if (rand.nextFloat() < droughtCutoff) { // Drought

                r = rand.nextInt(3) + 1;
                for (int w = 0; w < r; w++) {
                    for (int i = 0; i < FarmController.getPlots().size(); i++) {
                        if (FarmController.getPlots().get(i) != null)
                            FarmController.getPlots().get(i).drought();
                    }
                }


                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Your plants suffered a draught and lost "
                        + (int) r + " additional water!");
                a.show();

            } else if (rand.nextFloat() < locustCutoff) { // Locust

                int deaths = 0;
                locustCutoff = GameConfigurator.getDifficulty().equals("Egg") ? 0.1f
                        : (GameConfigurator.getDifficulty().equals("Chicken") ? 0.3f : 0.5f);

                for (int i = 0; i < FarmController.getPlots().size(); i++) {
                    if (FarmController.getPlots().get(i) == null) continue;
                    if (rand.nextFloat() < locustCutoff) {
                        if (!(FarmController.getPlots().get(i).getCrop().getPesticide())) {
                            FarmController.getPlots().get(i).setStageOfGrowth(-1);
                            deaths++;
                        }
                    }

                }

                Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                a.setContentText("Your plants suffer a locust storm and " + deaths + " plots died");
                a.show();

            }

            GameConfigurator.getScreens().get(GameConfigurator.ScreenName.FARM_SCREEN).update();

        }

        int currTime = GameConfigurator.getTime();
        String textStr = "Time: " + currTime + " day" + (currTime > 1 ? "s" : "");

        if (timeLabel != null) {

            timeLabel.setText(textStr);

        }
        returnToGame();

        boolean loss = GameConfigurator.checkLoss();

        if (loss) {

            GameConfigurator.changeToScreen(GameConfigurator.ScreenName.LOSS_SCREEN);

        }

        boolean win = GameConfigurator.checkWin();

        if (win) {

            GameConfigurator.changeToScreen(GameConfigurator.ScreenName.WIN_SCREEN);

        }

        // reset harvest and water count
        HarvestController.resetHarvestCount();
        resetWaterCount();

        return textStr;

    }

    public static void returnToGame() {

        GameConfigurator.changeToScreen(GameConfigurator.ScreenName.FARM_SCREEN);

    }

    public static void water(int plotNumber) {
        Player currPlayer = GameConfigurator.getPlayer();
        // no irrigation: water 10 times
        // with irrigation: water 60 times
        waterLimit = currPlayer.getInventory().findIrrigation() ? 60 : 10;
        if (waterCount >= waterLimit) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("Water hose quit for the rest of the day. "
                    + "Buy irrigation or wait until tomorrow to water more");
            alert.show();
        } else {
            Plot plot = FarmController.getPlots().get(plotNumber - 1);
            plot.water();
            waterCount++;
        }

    }

    public static void sprayPesticide(int plotNumber) {
        Player player = GameConfigurator.getPlayer();
        if (!(player.getInventory().findPesticide())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You have not purchased pesticide");
            alert.show();
        } else {
            Plot plot = FarmController.getPlots().get(plotNumber - 1);
            src.main.Player p = GameConfigurator.getPlayer();
            p.getInventory().removeItem("Pesticide");
            plot.sprayPesticide();
            returnToGame();
        }

    }

    public static void fertilize(int plotNumber) {
        src.main.Player player = src.main.GameConfigurator.getPlayer();
        if (!(player.getInventory().getItems().contains("Fertilizer"))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText("You have not purchased fertilizer");
            alert.show();
        } else {
            Plot plot = FarmController.getPlots().get(plotNumber - 1);
            player.getInventory().removeItem("Fertilizer");
            plot.fertilize();
            returnToGame();
        }


    }
}
