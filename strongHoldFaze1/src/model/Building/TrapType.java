package model.Building;

public enum TrapType {
    KILLING_PIT,
    DOG_CAGE;
    private int hitPoint;
    private int damage;
    private String texture;

    public String getTexture() {
        return texture;
    }
}
