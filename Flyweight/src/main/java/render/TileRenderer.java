package render;

import javafx.scene.canvas.GraphicsContext;
import map.GameMap;
import map.Tile;

import java.util.Objects;

public final class TileRenderer {
    private final TileGraphicFactory graphicFactory;
    private final int tileSize;

    public TileRenderer(TileGraphicFactory graphicFactory, int tileSize) {
        this.graphicFactory = Objects.requireNonNull(graphicFactory);
        if (tileSize <= 0) {
            throw new IllegalArgumentException("Tile size must be positive.");
        }
        this.tileSize = tileSize;
    }

    public void render(GameMap map, GraphicsContext graphics) {
        Objects.requireNonNull(map);
        Objects.requireNonNull(graphics);

        for (int row = 0; row < map.getRows(); row++) {
            for (int column = 0; column < map.getColumns(); column++) {
                Tile tile = map.getTile(row, column);
                TileGraphic sharedGraphic = graphicFactory.getGraphic(tile.getTerrainType());
                double x = tile.getColumn() * tileSize;
                double y = tile.getRow() * tileSize;
                sharedGraphic.draw(graphics, x, y, tileSize);
            }
        }
    }
}
