package model;

public class Map {
    private Cell[][] map;

    private int dimension;

    public Map(int dimension) {
        this.dimension = dimension;
        map = new Cell[dimension][dimension];
    }

    public Cell[][] getMap() {
        return map;
    }

    public int getDimension() {
        return dimension;
    }

//    public Cell getCellByPosition(int xPosition, int yPosition) {
//        re
//    }
}