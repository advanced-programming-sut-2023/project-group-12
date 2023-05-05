package model.people.fighter.soldier.warrior;

import model.people.fighter.UnitType;

public class Assassin extends Warrior{

    private boolean isShowForEnemy;

    public Assassin(int xCoordinate, int yCoordinate, UnitType unitType) {
        super(xCoordinate, yCoordinate, unitType);
    }

    @Override
    public void move(int xCoordinate, int yCoordinate) {

    }
}
