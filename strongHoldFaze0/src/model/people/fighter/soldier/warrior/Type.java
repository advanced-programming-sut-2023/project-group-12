package model.people.fighter.soldier.warrior;

public enum Type {
    ARABIAN_SWORDSMEN(0, 0, 0, 0, 0),
    BLACK_MONK(0, 0, 0, 0, 0),
    KNIGHT(0, 0, 0, 0, 0),
    SWORDSMEN(0, 0, 0, 0, 0),
    MACEMEN(0, 0, 0, 0, 0),
    PIKEMEN(0, 0, 0, 0, 0),
    SPEARMEN(0, 0, 0, 0, 0),
    ASSASSIN(0, 0, 0, 0, 0),
    ARCHER(0, 0, 0, 0, 0),
    CROSSBOWMAN(0, 0, 0, 0, 0),
    HORSE_ARCHER(0, 0, 0, 0, 0),
    ARCHER_BOW(0, 0, 0, 0, 0),
    SLINGERS(0, 0, 0, 0, 0),
    FIRE_THROWER(0, 0, 0, 0, 0);

    private int life;
    private int hitpoint;
    private int rangeFrom;
    private int rangeTo;
    private int speed;

    Type(int life, int hitpoint, int rangeFrom, int rangeTo, int speed) {
        this.life = life;
        this.hitpoint = hitpoint;
        this.rangeFrom = rangeFrom;
        this.rangeTo = rangeTo;
        this.speed = speed;
    }

    public int getLife() {
        return life;
    }

    public int getHitpoint() {
        return hitpoint;
    }

    public int getRangeFrom() {
        return rangeFrom;
    }

    public int getRangeTo() {
        return rangeTo;
    }

    public int getSpeed() {
        return speed;
    }
}
