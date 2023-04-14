package model.people.fighter.ballista.trebuchet;

import model.people.fighter.Fighter;

abstract public class Trebuchet extends Fighter {

    private Type type;
    public Trebuchet(int xCoordinate, int yCoordinate, Type type) {
        super(xCoordinate, yCoordinate);
        this.type = type;
    }
}
