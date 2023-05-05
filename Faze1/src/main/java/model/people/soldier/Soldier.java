package model.people.soldier;

import model.Kingdom;
import model.people.Unit;
import model.people.UnitType;


public class Soldier extends Unit {

    private Kingdom owner;

    private int hitPoint;

    private Situation situation;
    private UnitType unitType;



    public Soldier(int hitPoint, Kingdom owner, UnitType unitType) {
        super(owner, hitPoint);
        this.unitType = unitType;
        situation = Situation.STANDING;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public Kingdom getOwner() {
        return owner;
    }

    public Situation getSituation() {
        return situation;
    }

    public UnitType getUnitType() {
        return unitType;
    }
}
