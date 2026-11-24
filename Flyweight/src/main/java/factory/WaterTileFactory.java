package factory;

import map.TerrainType;
import map.Tile;


public final class WaterTileFactory extends TileFactory {
    @Override
    public Tile createTile(int row, int column) {
        return new Tile(TerrainType.WATER, row, column);
    }
}
