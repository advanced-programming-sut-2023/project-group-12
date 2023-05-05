package model.people.fighter.ballista.trebuchet;

import model.people.fighter.Attack;
import model.people.fighter.Fighter;
import model.people.fighter.UnitType;

abstract public class Trebuchet extends Fighter implements Attack {

    public Trebuchet(int xCoordinate, int yCoordinate, UnitType unitType) {
        super(xCoordinate, yCoordinate, unitType);
    }

    @Override
    public void attackToEnemy(int xCoordinate, int yCoordinate) {

    }
}
