package factory;


import map.Tile;

public abstract class TileFactory {
    public abstract Tile createTile(int row, int column);
}
