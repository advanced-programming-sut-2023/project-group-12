package model.people;

import model.Kingdom;

public class Unit {
    private Kingdom homeland;

    private int xPosition;

    private int yPosition;

    private UnitType unitType ;
    private int hitPoint;


    public Unit(Kingdom homeland, UnitType unitType, int xPosition, int yPosition) {
        this.homeland = homeland;
        this.unitType = unitType;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        hitPoint = unitType.getHitPoint();
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public Kingdom getHomeland() {
        return homeland;
    }

}
