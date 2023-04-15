package model;

public class Cell {
    private int x;
    private int y;
    // building, material, population, ...
    // TODO: show info
    private String texture;

    public void setTexture(String texture) {
        this.texture = texture;
    }
    public boolean isPassable () {
        return false;
    }
    public void clearCell () {

    }
    // add unit to cell
}
