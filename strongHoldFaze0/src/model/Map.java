package model;

import java.util.ArrayList;

public class Map {
    public ArrayList<ArrayList<Cell>> gameField;
    // maybe turn gameField to matrix of cells
    public Map(ArrayList<ArrayList<Cell>> gameField) {
        this.gameField = gameField;
    }
    public String showMap (int x, int y) {
        // show the map
        return "";
    }
}
