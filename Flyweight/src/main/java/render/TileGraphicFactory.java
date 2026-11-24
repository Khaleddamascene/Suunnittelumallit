package render;

import map.TerrainType;
import javafx.scene.paint.Color;


import java.util.EnumMap;
import java.util.Map;

public final class TileGraphicFactory {
    private final Map<TerrainType, TileGraphic> graphics =
            new EnumMap<>(TerrainType.class);

    public TileGraphicFactory() {
        graphics.put(TerrainType.GRASS, new TileGraphic(
                TerrainType.GRASS, Color.web("#78b85a"),
                Color.web("#397640"), Color.web("#456b39"),
                TileGraphic.Detail.GRASS));
        graphics.put(TerrainType.WATER, new TileGraphic(
                TerrainType.WATER, Color.web("#4ca6d8"),
                Color.web("#d5f3ff"), Color.web("#286987"),
                TileGraphic.Detail.WAVES));
        graphics.put(TerrainType.FOREST, new TileGraphic(
                TerrainType.FOREST, Color.web("#32734c"),
                Color.web("#184b36"), Color.web("#244c37"),
                TileGraphic.Detail.TREES));
        graphics.put(TerrainType.MOUNTAIN, new TileGraphic(
                TerrainType.MOUNTAIN, Color.web("#8b919d"),
                Color.web("#e5e8ed"), Color.web("#555d69"),
                TileGraphic.Detail.PEAK));
        graphics.put(TerrainType.SAND, new TileGraphic(
                TerrainType.SAND, Color.web("#dfc17a"),
                Color.web("#9c7941"), Color.web("#a98d54"),
                TileGraphic.Detail.GRAINS));
    }

    public TileGraphic getGraphic(TerrainType terrainType) {
        TileGraphic graphic = graphics.get(terrainType);
        if (graphic == null) {
            throw new IllegalArgumentException("No graphic for terrain: " + terrainType);
        }
        return graphic;
    }

    public int getCachedGraphicCount() {
        return graphics.size();
    }
}
