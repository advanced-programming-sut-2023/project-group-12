package model.map;

public enum TextureType {
    EARTH(true, false),
    GRAVEL(true, false),
    STONE_MINE(true, false),
    ROCK_NORTH(false, false),
    ROCK_EAST(false, false),
    ROCK_WEST(false, false),
    ROCK_SOUTH(false, false),
    IRON_MINE(true, false),
    GRASS(true, false),
    MEADOW(true, false),
    DENSE_MEADOW(true, false),
    OIL(true, true),
    PLAIN(true, true),
    SHALLOW_WATER(true, true),
    FOUNTAIN(false, true),
    SMALL_POND(false, true),
    BIG_POND(false, true),
    BEACH(true, true),
    SEA(false, true);

    private final boolean passable;

    private final boolean isWateryLand;

    TextureType(boolean passable, boolean isWateryLand) {
        this.passable = passable;
        this.isWateryLand = isWateryLand;
    }

    public boolean isWateryLand() {
        return isWateryLand;
    }

    public boolean isWatery() {
        return this.isWateryLand;
    }

    public boolean isPassable() {
        return passable;
    }
}
