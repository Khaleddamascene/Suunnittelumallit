package factory;



import map.GameMap;
import map.Tile;

import java.util.List;
import java.util.Random;

public final class RandomMapGenerator {
    private final List<TileFactory> tileFactories;
    private final Random random;

    public RandomMapGenerator() {
        this(System.nanoTime());
    }

    public RandomMapGenerator(long seed) {
        this.tileFactories = List.of(
                new GrassTileFactory(),
                new WaterTileFactory(),
                new ForestTileFactory(),
                new MountainTileFactory(),
                new SandTileFactory()
        );
        this.random = new Random(seed);
    }

    public GameMap generate(int rows, int columns) {
        if (rows <= 0 || columns <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive.");
        }

        Tile[][] tiles = new Tile[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                TileFactory factory = tileFactories.get(random.nextInt(tileFactories.size()));
                tiles[row][column] = factory.createTile(row, column);
            }
        }
        return new GameMap(tiles);
    }
}
