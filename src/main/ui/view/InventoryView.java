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
import javafx.stage.Screen;
import javafx.stage.Stage;
import src.main.ui.ScreenMaker;
import src.main.ui.controller.InventoryController;

import java.util.List;

public class InventoryView extends ScreenMaker {

    private InventoryController controller;

    public InventoryView(Stage stage) {

        super(stage);

        controller = new InventoryController();

    }

    @Override
    public Scene getScene() {
        // gets size of screen and stores it in Rectangle2D container
        Rectangle2D screenSize = Screen.getPrimary().getBounds();

        Text title = new Text("Inventory");
        title.setStyle("-fx-font: 36 arial; -fx-font-weight: bold");


        Button returnButton = new Button("Return");
        returnButton.setOnAction(e -> controller.returnToGame());

        ObservableList<String> inventoryListLeftItems = FXCollections.observableArrayList();
        ObservableList<String> inventoryListRightItems = FXCollections.observableArrayList();

        List<String> inventory = controller.getInventoryItems();

        for (int i = 0; i < 20; i++) {

            if (i >= inventory.size()) {
                break;
            } else if (i < 10) {
                inventoryListLeftItems.add(inventory.get(i));
            } else {
                inventoryListRightItems.add(inventory.get(i));
            }

        }

        ListView<String> inventoryListLeft = new ListView<>(inventoryListLeftItems);
        ListView<String> inventoryListRight = new ListView<>(inventoryListRightItems);

        HBox inventoryLists = new HBox(inventoryListLeft, inventoryListRight);
        inventoryLists.setAlignment(Pos.CENTER);
        inventoryLists.setPadding(new Insets(15));
        VBox vbox = new VBox(title, inventoryLists, returnButton);
        vbox.setPadding(new Insets(15));
        vbox.setAlignment(Pos.CENTER);

        return new Scene(vbox, screenSize.getWidth(), screenSize.getHeight());

    }

}
