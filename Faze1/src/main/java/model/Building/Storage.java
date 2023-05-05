package model.Building;

import model.Kingdom;

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
