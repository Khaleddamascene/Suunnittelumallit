package org.example;

import java.time.format.DateTimeFormatter;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;

public final class HistoryWindow {
    private final Stage stage = new Stage();

    public HistoryWindow(Stage owner, Controller controller) {
        stage.initOwner(owner);
        stage.setTitle("State history");

        ListView<IMemento> list = new ListView<>(controller.getSavedStates());
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HH:mm:ss.SSS");

        list.setCellFactory(view -> {
            ListCell<IMemento> cell = new ListCell<IMemento>() {
                @Override
                protected void updateItem(IMemento item, boolean empty) {
                    super.updateItem(item, empty);
                    setText(empty || item == null ? null
                            : (getIndex() + 1) + ". "
                            + item.getSavedAt().format(format) + " — "
                            + item.getDescription());
                }
            };
            // Handle clicks on actual rows, including an already selected row.
            cell.setOnMouseClicked(event -> {
                if (!cell.isEmpty()) {
                    controller.restoreFromHistory(cell.getItem());
                }
            });
            return cell;
        });

        VBox root = new VBox(10,
                new Label("Click a saved state to restore it. Ctrl+Z undoes the jump."),
                list);
        root.setPadding(new Insets(12));
        VBox.setVgrow(list, javafx.scene.layout.Priority.ALWAYS);
        Scene scene = new Scene(root, 600, 350);
        Gui.installHistoryShortcuts(scene, controller);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
        stage.toFront();
    }
}

