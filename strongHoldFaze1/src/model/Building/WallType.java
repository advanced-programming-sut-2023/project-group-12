package model.Building;

public enum WallType {
    LONG_WALL,
    SHORT_WALL,
    STAIRCASE;
    private String texture;

    public String getTexture() {
        return texture;
    }
}
