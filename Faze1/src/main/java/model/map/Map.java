package model.map;

public class Map {

    private int dimension;
    private Cell[][] map;

    private int lastX;
    private int lastY;

    public Map(int dimension) {
        map = new Cell[dimension][dimension];
        dimension = dimension;
    }

    public Cell[][] getMap() {
        return map;
    }

    public int getDimension() {
        return dimension;
    }

    public int getLastX() {
        return lastX;
    }

    public void setLastX(int lastX) {
        this.lastX = lastX;
    }

    public int getLastY() {
        return lastY;
    }

    public void setLastY(int lastY) {
        this.lastY = lastY;
    }
}