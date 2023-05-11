package model;


import model.Building.Building;
import model.people.Unit;

import java.util.ArrayList;

public class Cell {

    private TextureType type = TextureType.EARTH;
    private Tree tree = null;
    private Building building = null;
    private ArrayList<Unit> units = new ArrayList<Unit>();

    public TextureType getTextureType() {
        return type;
    }

    public void setTextureType(TextureType type) {
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


}
