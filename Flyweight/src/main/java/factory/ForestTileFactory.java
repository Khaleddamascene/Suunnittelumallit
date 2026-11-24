package factory;

import map.TerrainType;
import map.Tile;

public final class ForestTileFactory extends TileFactory {
    @Override
    public Tile createTile(int row, int column) {
        return new Tile(TerrainType.FOREST, row, column);
    }
}
