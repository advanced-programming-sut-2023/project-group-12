package model.people.fighter.soldier.warrior;

import model.people.Unit;
import model.people.fighter.soldier.Soldier;

public class Warrior extends Soldier {

    private Type type;
    public Warrior(int xCoordinate, int yCoordinate, Type type) {
        super(xCoordinate, yCoordinate);
        this.type = type;
    }

    public void attackToEnemy(int xCoordinate, int yCoordinate) {

    }
}
