package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Wall extends Building{
    private int height;
    private boolean isStaircase;

    private int hitPoint;


    public Wall(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        hitPoint = buildingType.getHitPoint();
    }
}
