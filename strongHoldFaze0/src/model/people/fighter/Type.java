package model.people.fighter;

import java.lang.ref.PhantomReference;

public enum Type {
<<<<<<< HEAD
//    ARABIAN_SWORDSMEN(0, 0, 0, 0, 0),
//    BLACK_MONK(0, 0, 0, 0, 0),
//    KNIGHT(0, 0, 0, 0, 0),
//    SWORDSMEN(0, 0, 0, 0, 0),
//    MACEMEN(0, 0, 0, 0, 0),
//    PIKEMEN(0, 0, 0, 0, 0),
//    SPEARMEN(0, 0, 0, 0, 0),
//    ASSASSIN(0, 0, 0, 0, 0),
//    ARCHER(0, 0, 0, 0, 0),
//    CROSSBOWMAN(0, 0, 0, 0, 0),
//    HORSE_ARCHER(0, 0, 0, 0, 0),
//    ARCHER_BOW(0, 0, 0, 0, 0),
//    SLINGERS(0, 0, 0, 0, 0),
//    FIRE_THROWER(0, 0, 0, 0, 0),
//    SLAVE(0, 0, 0, 0, 0),
//    ENGINEER(0, 0, 0, 0, 0),
//    LADDERMAN(0, 0, 0, 0, 0),
//    TUNNELER(0, 0, 0, 0, 0),
//    COUNTERWEIGHT_TREBUCHET(0, 0, 0, 0, 0),
//    STONE_TREBUCHET(0, 0, 0, 0, 0),
//    FIRE_TREBUCHET(0, 0, 0, 0, 0);
;
=======
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
    FIRE_THROWER(0, 0, 0, 0, 0),
    SLAVE(0, 0, 0, 0, 0),
    ENGINEER(0, 0, 0, 0, 0),
    LADDERMAN(0, 0, 0, 0, 0),
    TUNNELER(0, 0, 0, 0, 0),
    COUNTERWEIGHT_TREBUCHET(0, 0, 0, 0, 0),
    STONE_TREBUCHET(0, 0, 0, 0, 0),
    FIRE_TREBUCHET(0, 0, 0, 0, 0);

>>>>>>> 569cb64bfd32ce3f97e6b27a0ba3d1a658adc27f
    private int life;
    private int hitpoint;
    private int rangeFrom;
    private int rangeTo;
    private int speed;
    private int goldCost;
    private int armourCost;
    private int maceCost;
    private int swordCost;
    private int spearCost;
    private int engineerCost;

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
