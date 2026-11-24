package factory;

import map.TerrainType;
import map.Tile;

public final class GrassTileFactory extends TileFactory {
    @Override
    public Tile createTile(int row, int column) {
        return new Tile(TerrainType.GRASS, row, column);
    }
}
