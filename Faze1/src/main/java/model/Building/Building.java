package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Building {
    private BuildingType buildingType;
    private int hitPoint;
    private int xPosition;
    private int yPosition;
    private ArrayList<Unit> people;
    private int height;
    private HashMap<String,Integer>price;
    private Kingdom owner;


    public Building(BuildingType buildingType, Kingdom owner, int xPosition, int yPosition) {
        this.buildingType = buildingType;
        this.owner = owner;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        hitPoint = buildingType.getHitPoint();
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void setHeight(Building building) {
        if(building.getBuildingType().getBuildingClass() == Tower.class){
            height = 3;
        }else if(building.getBuildingType().getBuildingClass() == Wall.class && building.getBuildingType().getBuildingName().equals("stair")){
            height = 1;
        }
        else{
            height = 2;
        }

    }

    public Kingdom getOwner() {
        return owner;
    }

    public int getHeight() {
        return height;
    }

    public BuildingType getBuildingType() {
        return buildingType;
    }

    public void resetHealth(){
        hitPoint = buildingType.getHitPoint();
    }

    public int getxPosition() {
        return xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }
}
