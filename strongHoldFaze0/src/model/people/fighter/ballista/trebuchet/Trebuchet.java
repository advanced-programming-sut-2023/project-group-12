package model.people.fighter.ballista.trebuchet;

import model.people.fighter.Attack;
import model.people.fighter.Fighter;
import model.people.fighter.Type;

abstract public class Trebuchet extends Fighter implements Attack {

    public Trebuchet(int xCoordinate, int yCoordinate, Type type) {
        super(xCoordinate, yCoordinate, type);
    }

    @Override
    public void attackToEnemy(int xCoordinate, int yCoordinate) {

    }
}
