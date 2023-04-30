package model.map;

public class Map {

    private int dimension;
    private Cell[][] map;

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
}