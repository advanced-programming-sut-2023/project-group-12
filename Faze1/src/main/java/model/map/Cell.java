package model.map;


import model.Building.Building;
import model.people.Unit;

import java.util.ArrayList;

public class Cell {

    private Type type = Type.EARTH;
    private Tree tree = null;
    private Building building = null;
    private ArrayList<Unit> units = new ArrayList<Unit>();
    private boolean isInThePath = false;
    private Cell father;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
}
