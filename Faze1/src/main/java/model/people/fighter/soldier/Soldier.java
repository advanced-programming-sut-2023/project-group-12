package model.people.fighter.soldier;

import model.people.fighter.Attack;
import model.people.fighter.Fighter;
import model.people.fighter.Movable;
import model.people.fighter.Type;

abstract public class Soldier extends Fighter implements Movable, Attack {
    Situation situation;
    public Soldier(int xCoordinate, int yCoordinate, Type type) {
        super(xCoordinate, yCoordinate, type);
    }

    public void move(int xCoordinate, int yCoordinate) {
        Movable.moving(this, xCoordinate, yCoordinate);
    }

    public void disband() {

    }

    @Override
    public void attackToEnemy(int xCoordinate, int yCoordinate) {

    }

    public Situation getSituation() {
        return situation;
    }

    public void setSituation(Situation situation) {
        this.situation = situation;
    }
}
