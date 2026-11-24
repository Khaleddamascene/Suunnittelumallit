package render;

import map.TerrainType;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.Objects;

public final class TileGraphic {
    private final TerrainType terrainType;
    private final Color baseColor;
    private final Color detailColor;
    private final Color borderColor;
    private final Detail detail;

    public TileGraphic(TerrainType terrainType, Color baseColor,
                       Color detailColor, Color borderColor, Detail detail) {
        this.terrainType = Objects.requireNonNull(terrainType);
        this.baseColor = Objects.requireNonNull(baseColor);
        this.detailColor = Objects.requireNonNull(detailColor);
        this.borderColor = Objects.requireNonNull(borderColor);
        this.detail = Objects.requireNonNull(detail);
    }

    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void draw(GraphicsContext graphics, double x, double y, double size) {
        graphics.save();
        graphics.setFill(baseColor);
        graphics.fillRect(x, y, size, size);
        drawDetail(graphics, x, y, size);
        graphics.setStroke(borderColor);
        graphics.setLineWidth(0.8);
        graphics.strokeRect(x, y, size, size);
        graphics.restore();
    }

    private void drawDetail(GraphicsContext graphics, double x, double y, double size) {
        graphics.setFill(detailColor);
        graphics.setStroke(detailColor);
        graphics.setLineWidth(1.5);

        switch (detail) {
            case GRASS -> {
                graphics.strokeLine(x + size * 0.25, y + size * 0.72,
                        x + size * 0.30, y + size * 0.56);
                graphics.strokeLine(x + size * 0.30, y + size * 0.72,
                        x + size * 0.36, y + size * 0.59);
                graphics.strokeLine(x + size * 0.69, y + size * 0.38,
                        x + size * 0.74, y + size * 0.25);
            }
            case WAVES -> {
                graphics.strokeLine(x + size * 0.12, y + size * 0.32,
                        x + size * 0.43, y + size * 0.32);
                graphics.strokeLine(x + size * 0.53, y + size * 0.65,
                        x + size * 0.88, y + size * 0.65);
            }
            case TREES -> {
                graphics.fillOval(x + size * 0.20, y + size * 0.20,
                        size * 0.28, size * 0.36);
                graphics.fillOval(x + size * 0.52, y + size * 0.40,
                        size * 0.27, size * 0.34);
            }
            case PEAK -> {
                graphics.fillPolygon(
                        new double[]{x + size * 0.18, x + size * 0.48, x + size * 0.80},
                        new double[]{y + size * 0.72, y + size * 0.25, y + size * 0.72},
                        3);
                graphics.setStroke(baseColor);
                graphics.strokeLine(x + size * 0.41, y + size * 0.36,
                        x + size * 0.49, y + size * 0.47);
            }
            case GRAINS -> {
                graphics.fillOval(x + size * 0.24, y + size * 0.28, 2.5, 2.5);
                graphics.fillOval(x + size * 0.67, y + size * 0.52, 2.5, 2.5);
                graphics.fillOval(x + size * 0.43, y + size * 0.76, 2.5, 2.5);
            }
        }
    }

    public enum Detail {
        GRASS, WAVES, TREES, PEAK, GRAINS
    }
}
