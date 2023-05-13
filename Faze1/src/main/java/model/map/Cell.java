package model.map;


import model.Building.Building;
import model.people.Unit;

import java.util.ArrayList;

public class Cell {

    private int xCoordinate;
    private int yCoordinate;

    public Cell(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    private TextureType TextureType = model.map.TextureType.EARTH;

    private Tree tree = null;
    private Building building = null;
    private int height = 0;
    private ArrayList<Unit> units = null;
    private boolean isInThePath = false;
    private Cell father = null;

    public void setFather(Cell father) {
        this.father = father;
    }

    public Cell getFather() {
        return father;
    }

    public boolean isInThePath() {
        return isInThePath;
    }

    public void setInThePath(boolean inThePath) {
        isInThePath = inThePath;
    }

    public void setTextureType(model.map.TextureType textureType) {
        TextureType = textureType;
    }

    public Tree getTree() {
        return tree;
    }

    public void setTree(Tree tree) {
        this.tree = tree;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public ArrayList<Unit> getUnits() {
        if (units == null)
            units = new ArrayList<>();
        return units;
    }

    public void addUnits(Unit unit) {
        units.add(unit);
    }

    public void moveUnit(Unit unit) {
        units.remove(unit);
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }
    private boolean isPassable;

    public boolean isPassable() {
        return isPassable;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public TextureType getTextureType() {
        return TextureType;
    }
}
