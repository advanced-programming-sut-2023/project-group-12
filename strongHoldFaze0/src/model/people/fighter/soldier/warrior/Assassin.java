package model.people.fighter.soldier.warrior;

import model.people.fighter.Type;

public class Assassin extends Warrior{

    private boolean isShowForEnemy;

    public Assassin(int xCoordinate, int yCoordinate, Type type) {
        super(xCoordinate, yCoordinate, type);
    }

    @Override
    public void move(int xCoordinate, int yCoordinate) {

    }
}
