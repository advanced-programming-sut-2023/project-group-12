package model.Building;

import model.Kingdom;

public class Wall extends Building{
    private int height;
    private boolean isStaircase;

    private int hitPoint;


    public Wall(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        hitPoint = buildingType.getHitPoint();
    }
}
