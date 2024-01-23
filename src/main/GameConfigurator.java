package src.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import src.main.ui.controller.FarmController;
import src.main.ui.view.PlantOptionsView;
import src.main.ui.view.PlantView;
import src.main.ui.view.HarvestScreen;
import src.main.ui.view.MarketView;
import src.main.ui.view.LossView;
import src.main.ui.ScreenMaker;
import src.main.ui.view.ConfigurationScreen;
import src.main.ui.view.FarmScreen;
import src.main.ui.view.InventoryView;
import src.main.ui.view.WelcomeScreen;
import src.main.ui.view.WaterScreen;
import src.main.ui.view.PesticideScreen;
import src.main.ui.view.FertilizeView;
import src.main.ui.view.WinView;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * Entry point for the game and game state controller.
 * Keeps track strictly of the game state, not the player state.
 *
 * Also provides methods to update state and other
 * utility functions related to the overall game state.
 *
 * @author Dylan Siegler
 * @version 1.0.0
 */
public class GameConfigurator extends Application {

    private static Map<ScreenName, ScreenMaker> screens = new HashMap<>();
    private static Stage stage;
    private static String difficulty;
    private static int time = 0;

    private static MediaPlayer mediaPlayer;

    /**
     * Stores the current player object
     */
    private static Player player;

    /**
     * Enum controlling the UI state of the game.
     *
     * The `implementingClass` is simply a marker attribute to specify which class actually sources
     * the screen redundant but easier way to access the class of a Scene.
     */
    public enum ScreenName {

        WELCOME_SCREEN(WelcomeScreen.class),
        CONFIGURATION_SCREEN(ConfigurationScreen.class),
        FARM_SCREEN(FarmScreen.class),
        INVENTORY_SCREEN(InventoryView.class),
        MARKET_SCREEN(MarketView.class),
        HARVEST_SCREEN(HarvestScreen.class),
        WATER_SCREEN(WaterScreen.class),
        PLANT_SCREEN(PlantView.class),
        PLANT_OPTIONS_SCREEN(PlantOptionsView.class),
        PESTICIDE_SCREEN(PesticideScreen.class),
        FERTILIZE_SCREEN(FertilizeView.class),
        LOSS_SCREEN(LossView.class),
        WIN_SCREEN(WinView.class);

        ScreenName(Class implementingClass) {

            /* Skeleton constructor */

        }

    }

    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage stage) {

        // Play sound
        String musicFile = "src/main/audio/soundtrack.wav";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play();

        GameConfigurator.player = new Player();
        // Create and store all scenes up front
        GameConfigurator.stage = stage;
        screens.put(ScreenName.WELCOME_SCREEN, new WelcomeScreen(stage));
        screens.put(ScreenName.CONFIGURATION_SCREEN, new ConfigurationScreen(stage));
        screens.put(ScreenName.FARM_SCREEN, new FarmScreen(stage));
        screens.put(ScreenName.INVENTORY_SCREEN, new InventoryView(stage));
        screens.put(ScreenName.MARKET_SCREEN, new MarketView(stage));
        screens.put(ScreenName.HARVEST_SCREEN, new HarvestScreen(stage));
        screens.put(ScreenName.WATER_SCREEN, new WaterScreen(stage));
        screens.put(ScreenName.PLANT_SCREEN, new PlantView(stage));
        screens.put(ScreenName.PLANT_OPTIONS_SCREEN, new PlantOptionsView(stage));
        screens.put(ScreenName.PESTICIDE_SCREEN, new PesticideScreen(stage));
        screens.put(ScreenName.FERTILIZE_SCREEN, new FertilizeView(stage));
        screens.put(ScreenName.LOSS_SCREEN, new LossView(stage));
        screens.put(ScreenName.WIN_SCREEN, new WinView(stage));

        // Start with the main scene
        stage.setScene(screens.get(ScreenName.WELCOME_SCREEN).getScene());
        stage.setTitle("Farm Guardians");
        stage.show();

    }

    public static void advanceTime() {

        GameConfigurator.setTime(GameConfigurator.getTime() + 1);

        for (ScreenName sn : screens.keySet()) {

            screens.get(sn).update();

        }

    }

    public static boolean checkLoss() {

        boolean allDead = true;

        for (src.main.Plot p : FarmController.getPlots()) {

            if (p != null && p.getCrop() != null && p.getStageOfGrowth() >= 0) {

                allDead = false;
                break;

            }

        }

        return allDead && player.getMoney() <= 1;

    }

    public static boolean checkWin() {

        return player.getMoney() >= 100.1;  // Select Egg > sell anything to demo

    }

    public static int getTime() {

        return time;

    }

    public static void setTime(int time) {

        GameConfigurator.time = time;

    }

    /**
     * Changes the current screen of the game to the given screen. Does not update the game state
     * @param newScreen The screen to transition to
     */
    public static void changeToScreen(ScreenName newScreen) {

        if (stage != null) {

            stage.setScene(GameConfigurator.getScreens().get(newScreen).getScene());
            GameConfigurator.getScreens().get(newScreen).onActivated();

        }

    }

    /**
     * Gets the current scene
     * @return Current scene
     */
    public static Scene getCurrentScene() {

        return stage.getScene();

    }

    /**
     * Sets the game's current player
     * @param player New player
     */
    public static void setPlayer(src.main.Player player) {

        GameConfigurator.player = player;

    }

    /**
     * Gets the current player
     * @return Current player
     */
    public static Player getPlayer() {

        return player;

    }

    /**
     * Gets the map of screens
     * @return Scene map
     */
    public static Map<ScreenName, ScreenMaker> getScreens() {

        return screens;

    }

    /**
     * Gets the Scene instance for a given SceneName
     * @param name Name of the Scene to get
     * @return The Scene instance
     */
    public static Scene getScene(ScreenName name) {

        return getScreens().get(name).getScene();

    }

    public static void setDifficulty(String difficulty) {
        GameConfigurator.difficulty  = difficulty;

        if (difficulty.equals("Egg")) {

            player.setMoney(100d);

        } else if (difficulty.equals("Chicken")) {

            player.setMoney(50d);

        } else {

            player.setMoney(1d);

        }

    }

    public static String getDifficulty() {
        return GameConfigurator.difficulty;
    }

}