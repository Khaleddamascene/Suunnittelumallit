import factory.RandomMapGenerator;
import map.GameMap;
import render.TileGraphicFactory;
import render.TileRenderer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GameApplication extends Application {
    private static final int ROWS = 16;
    private static final int COLUMNS = 22;
    private static final int TILE_SIZE = 32;

    private final RandomMapGenerator mapGenerator = new RandomMapGenerator();
    private final TileGraphicFactory graphicFactory = new TileGraphicFactory();
    private final TileRenderer renderer = new TileRenderer(graphicFactory, TILE_SIZE);
    private final Label status = new Label();

    @Override
    public void start(Stage stage) {
        GameMap map = mapGenerator.generate(ROWS, COLUMNS);
        Canvas canvas = new Canvas(COLUMNS * TILE_SIZE, ROWS * TILE_SIZE);
        renderer.render(map, canvas.getGraphicsContext2D());

        Label title = new Label("RPG-kartta | Factory Method + Flyweight");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Button newMapButton = new Button("Luo uusi kartta");
        newMapButton.setOnAction(event -> {
            GameMap newMap = mapGenerator.generate(ROWS, COLUMNS);
            renderer.render(newMap, canvas.getGraphicsContext2D());
            updateStatus(newMap);
        });

        HBox header = new HBox(16, title, newMapButton);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(14));

        VBox legend = createLegend();
        legend.setPadding(new Insets(14));

        status.setStyle("-fx-font-size: 12px; -fx-text-fill: #354052;");
        BorderPane root = new BorderPane();
        root.setTop(header);
        root.setCenter(canvas);
        root.setRight(legend);
        root.setBottom(status);
        BorderPane.setAlignment(canvas, Pos.CENTER);
        BorderPane.setMargin(canvas, new Insets(4, 12, 8, 12));
        BorderPane.setMargin(status, new Insets(0, 14, 12, 14));

        updateStatus(map);

        stage.setTitle("Flyweight RPG Map");
        stage.setScene(new Scene(root, 1040, 590));
        stage.setMinWidth(900);
        stage.setMinHeight(520);
        stage.show();
    }

    private VBox createLegend() {
        VBox legend = new VBox(10);
        Label title = new Label("Maastotyypit");
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        legend.getChildren().add(title);

        addLegendItem(legend, "Nurmikko", Color.web("#78b85a"));
        addLegendItem(legend, "Vesi", Color.web("#4ca6d8"));
        addLegendItem(legend, "Metsä", Color.web("#32734c"));
        addLegendItem(legend, "Vuoristo", Color.web("#8b919d"));
        addLegendItem(legend, "Hiekka", Color.web("#dfc17a"));
        return legend;
    }

    private void addLegendItem(VBox legend, String name, Color color) {
        Rectangle swatch = new Rectangle(16, 16, color);
        swatch.setStroke(Color.web("#263342"));
        HBox item = new HBox(8, swatch, new Label(name));
        item.setAlignment(Pos.CENTER_LEFT);
        legend.getChildren().add(item);
    }

    private void updateStatus(GameMap map) {
        status.setText("Karttaruutuja: " + (map.getRows() * map.getColumns())
                + "    |    Jaettuja grafiikkaolioita: "
                + graphicFactory.getCachedGraphicCount()
                + " (yksi maastotyyppiä kohti)");
    }

}
