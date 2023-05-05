package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage extends Building{
    private BuildingType buildingType;
    private int capacity;
    private String storedType;
    private int hitPoint;

    public Storage(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        hitPoint = buildingType.getHitPoint();
    }


    public boolean isFull () {
        return false;
    }
}
