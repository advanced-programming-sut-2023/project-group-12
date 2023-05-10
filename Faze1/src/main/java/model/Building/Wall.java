package model.Building;

import model.Kingdom;

public class Wall extends Building{
    private boolean isStaircase;
    private int hitPoint;

    public Wall(BuildingType buildingType, Kingdom owner, int xPosition, int yPosition) {
        super(buildingType, owner, xPosition, yPosition);
        hitPoint = buildingType.getHitPoint();
    }
}
