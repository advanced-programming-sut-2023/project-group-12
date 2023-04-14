package model.people.fighter.soldier;

import model.people.fighter.Fighter;
import model.people.fighter.Movable;

abstract public class Soldier extends Fighter implements Movable {
    Situation situation;
    public Soldier(int xCoordinate, int yCoordinate) {
        super(xCoordinate, yCoordinate);
    }

    public void move(int xCoordinate, int yCoordinate) {
        Movable.moving(this, xCoordinate, yCoordinate);
    }

    public void disband() {

    }
}
