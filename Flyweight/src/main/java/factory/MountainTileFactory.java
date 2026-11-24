package factory;

import map.TerrainType;
import map.Tile;

public final class MountainTileFactory extends TileFactory {
    @Override
    public Tile createTile(int row, int column) {
        return new Tile(TerrainType.MOUNTAIN, row, column);
    }
}
