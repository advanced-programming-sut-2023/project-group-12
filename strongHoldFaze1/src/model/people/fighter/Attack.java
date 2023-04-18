package model.people.fighter;

public interface Attack {
    static void attackTo(Fighter fighter, int xCoordinate, int yCoordinate) {

    }

    public void attackToEnemy(int xCoordinate, int yCoordinate);
}
