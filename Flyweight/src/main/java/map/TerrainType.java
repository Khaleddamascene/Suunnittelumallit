package map;

public enum TerrainType {
    GRASS("Nurmikko"),
    WATER("Vesi"),
    FOREST("Metsä"),
    MOUNTAIN("Vuoristo"),
    SAND("Hiekka");

    private final String displayName;

    TerrainType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
