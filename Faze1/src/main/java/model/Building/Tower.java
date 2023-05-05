package model.Building;

import model.Kingdom;

public class Tower extends Building{

    private int fireRange;
    private int defendRange;
    private int hitPoint;

    public Tower(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        hitPoint = buildingType.getHitPoint();
    }
}
