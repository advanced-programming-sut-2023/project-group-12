package model.Equipment;

import model.Kingdom;

public class Equipment {
    private int hitPoint;

    private EquipmentType equipmentType;

    private int xPosition;

    private int yPosition;

    private Kingdom owner;

    public Equipment(EquipmentType equipmentType, int xPosition, int yPosition, Kingdom owner) {
        this.equipmentType = equipmentType;
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.owner = owner;
        hitPoint = equipmentType.getHitPoint();
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getyPosition() {
        return yPosition;
    }

    public int getxPosition() {
        return xPosition;
    }

    public Kingdom getOwner() {
        return owner;
    }

    public EquipmentType getEquipmentType() {
        return equipmentType;
    }
}
