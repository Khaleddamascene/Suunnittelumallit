package map;

import java.util.Objects;

public final class Tile {
    private final TerrainType terrainType;
    private final int row;
    private final int column;

    public Tile(TerrainType terrainType, int row, int column) {
        this.terrainType = Objects.requireNonNull(terrainType);
        this.row = row;
        this.column = column;
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
