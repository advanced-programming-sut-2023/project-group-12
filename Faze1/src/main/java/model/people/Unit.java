package model.people;

import model.Kingdom;

public class Unit {
    private Kingdom homeland;

    private int xPosition;

    private int yPosition;

    private UnitType unitType ;
    private int hitPoint;
    private String mode;
    private int speed;

    private boolean isBusy;

    public int getSpeed() {
        return speed;
    }

    public Unit(Kingdom homeland, UnitType unitType, int xPosition, int yPosition) {
        this.homeland = homeland;
        this.unitType = unitType;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        hitPoint = unitType.getHitPoint();
        isBusy = false;
        this.hitPoint = unitType.getHitPoint();
        this.speed = unitType.getMoveSpeed() * 10;
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

    public int getxPosition() {
        return xPosition;
    }

    public void setxPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public int getyPosition() {
        return yPosition;
    }

    public void setyPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
    public boolean isBusy(){
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
