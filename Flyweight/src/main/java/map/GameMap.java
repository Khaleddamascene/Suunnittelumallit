package map;

import java.util.Objects;

public final class GameMap {
    private final Tile[][] tiles;

    public GameMap(Tile[][] tiles) {
        Objects.requireNonNull(tiles);
        if (tiles.length == 0 || tiles[0].length == 0) {
            throw new IllegalArgumentException("Map must have at least one row and column.");
        }

        int columns = tiles[0].length;
        for (Tile[] row : tiles) {
            if (row.length != columns) {
                throw new IllegalArgumentException("Map rows must have equal lengths.");
            }
            for (Tile tile : row) {
                Objects.requireNonNull(tile, "Map tiles cannot be null.");
            }
        }
        this.tiles = tiles;
    }

    public int getRows() {
        return tiles.length;
    }

    public int getColumns() {
        return tiles[0].length;
    }

    public Tile getTile(int row, int column) {
        return tiles[row][column];
    }
}
