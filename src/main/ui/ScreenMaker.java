package src.main.ui;

import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Abstract class that all screens must extend.
 *
 * To change from one screen to another, do:
 * @<code>
 *     GameController.changeToScreen(GameController.ScreenName.SCREEN_NAME);
 * </code>
 *
 * @author Dylan Siegler
 * @version 1.0.0
 */
public abstract class ScreenMaker {

    protected Stage stage;

    public ScreenMaker(Stage stage) {

        this.stage = stage;

    }

    /**
     * Returns the scene object for this screen
     * @return Constructed scene with all hooks and controllers attached
     */
    public abstract Scene getScene();

    /**
     * Gets stage for this screen
     * @return Scene
     */
    public Stage getStage() {

        return stage;

    }

    /**
     * Method that is called when this screen is changed to.
     */
    public void onActivated() {



    }

    public void update() {



    }

}
