package model.map;

import model.Kingdom;

import java.util.ArrayList;

public class Map {

    private int dimension;
    private Cell[][] map;

    private ArrayList<Cell> headSquares;
    private ArrayList<Kingdom> kingdoms;

    private int lastX;
    private int lastY;

    public Map(int dimension, int kingdomNumber) {
        map = new Cell[dimension][dimension];
        for (int i = 0; i < dimension; i++)
            for (int j = 0; j < dimension; j++)
                map[i][j] = new Cell();
        this.dimension = dimension;
        kingdoms = new ArrayList<>();
        headSquares = new ArrayList<>();
        for (int i = 0; i < kingdomNumber; i++) {
            kingdoms.add(new Kingdom());
            switch (i) {
                case 0 :
                    headSquares.add(map[0][0]);
                    break;
                case 1 :
                    headSquares.add(map[dimension - 1][dimension - 1]);
                    break;
                case 2 :
                    headSquares.add(map[0][dimension - 1]);
                    break;
                case 3 :
                    headSquares.add(map[dimension - 1][0]);
                    break;
                case 4 :
                    headSquares.add(map[0][dimension / 2]);
                    break;
                case 5 :
                    headSquares.add(map[dimension / 2][0]);
                    break;
                case 6 :
                    headSquares.add(map[dimension - 1][dimension / 2]);
                    break;
                case 7 :
                    headSquares.add(map[dimension / 2][dimension - 1]);
                    break;
            }
        }
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