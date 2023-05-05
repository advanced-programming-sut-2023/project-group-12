package model.people.fighter;

public enum UnitType {
    //name, hitPoint, attackPower, defensePower, rangeFrom, rangeTo, moveSpeed, attackSpeed, attackAccuracy,
//    ARCHER( 0, 0, 0, 0),
//    ARABIAN_SWORDSMEN(0, 0, 0, 0, 0),
//    BLACK_MONK(0, 0, 0, 0, 0),
//    KNIGHT(0, 0, 0, 0, 0),
//    SWORDSMEN(0, 0, 0, 0, 0),
//    MACEMEN(0, 0, 0, 0, 0),
//    PIKEMEN(0, 0, 0, 0, 0),
//    SPEARMEN(0, 0, 0, 0, 0),
//    ASSASSIN(0, 0, 0, 0, 0),
//
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
    KING(800, 160, 0.2, 2, 1, 0, 0, 0, 0, null, null, false, false, false,"king"),
    ARCHER(200,100,0.15,4,10,4,12,0.3,0,WeaponType.BOW, null,false,false,false,"archer"),
    CROSSBOW_MAN(250, 120, 0.2, 3, 5, 0, 20, 0.1, 0, WeaponType.CROSSBOW, WeaponType.LEATHER_ARMOR, false, false, false, "crossbow man"),
    SPEAR_MAN(150, 180, 0.1, 3 ,2, 0, 8, 0.1, 2, WeaponType.SPEAR, null, true, true, false, "spear man"),
    PIKE_MAN(500, 200, 0.3, 2, 1, 0, 20, 0, 0, WeaponType.PIKE, WeaponType.METAL_ARMOR, false, false, false, "pike man"),
    MACE_MAN(350, 300, 0.2, 3, 1, 0, 20, 0, 0, WeaponType.MACE, WeaponType.LEATHER_ARMOR, true, false, false, "mace man"),
    SWORDS_MAN(250, 350, 0.27, 1, 2, 0, 40, 0, 3, WeaponType.SWORDS, WeaponType.METAL_ARMOR, false, true, false, "swordsman"),
    KNIGHT(600, 400, 0.35, 4, 4, 0, 120, 0, 0, WeaponType.SWORDS, WeaponType.METAL_ARMOR, false, false, false, "knight"),
    TUNNELER(100, 100, 0.1, 3, 1, 0, 30, 0, 2, null, null, false, true, false, "tunneler"),
    LADDER_MAN(80, 0, 0.05, 3, 1, 0, 6, 0, 2, null, null, true, false, false, "ladder man"),
    ENGINEER(80, 0, 0.05, 3, 0, 0, 30, 0, 3, null, null, false, true, false, "engineer"),
    SOLDIER_ENGINEER(80, 100, 0.05, 2, 1, 0, 0, 0, 0, WeaponType.OIL, null, false, false, false, "oil engineer"),
    BLACK_MONK(200, 100, 0.15, 1, 1, 0, 10, 0.2, 0, null, null, false, false, false, "black monk"),

    ARCHER_BOW(200, 100, 0.12, 3, 11, 5, 75, 0.3, 0, WeaponType.BOW, null, false, false, true, "archer bow"),
    SLAVE(50, 40, 0, 3, 1, 0, 5, 0, 4, null, null, false, true, true, "slave"),
    SLINGER(100, 100, 0.1, 3, 2, 0, 12, 0.2, 0, null, null, false, false, true, "slinger"),
    ASSASSIN(400, 300, 0.3, 2, 3, 0, 100, 0, 0, null, null, true, false, true, "assassin"),
    HORSE_ARCHER(300, 120, 0.2, 5, 8, 3, 60, 0.5, 0, WeaponType.BOW, null, false, false, true, "horse archer"),
    ARABIAN_SWORDSMAN(500, 220, 0.2, 4, 2, 0, 80, 0, 0, WeaponType.SWORDS, WeaponType.LEATHER_ARMOR, false, false, true, "arabian swordsman"),
    FIRE_THROWER(150, 200, 0.1, 4, 2, 0, 70, 0.1, 0, null, null, false, false, true, "fire throwers"),
;
    int hitPoint;
    int attackPower;
    double defensePower;
    double speed;
    int range;
    int secondRange;
    int cost;
    double precision;
    int delay;
    WeaponType weapon;
    WeaponType weapon2;
    boolean canClimb;
    /*boolean canThrowLadders;*/
    boolean canDigDitch;
    boolean isArab;
    String name;

    UnitType(int hitPoint, int attackPower, double defensePower, double speed, int range, int secondRange, int cost,
             double precision, int delay, WeaponType weapon, WeaponType weapon2, boolean canClimb,/* boolean canThrowLadders,*/ boolean canDigDitch, boolean isArab, String name) {
        this.hitPoint = hitPoint;
        this.attackPower = attackPower;
        this.defensePower = defensePower;
        this.speed = speed;
        this.range = range;
        this.secondRange = secondRange;
        this.cost = cost;
        this.precision = precision;
        this.delay = delay;
        this.weapon = weapon;
        this.weapon2 = weapon2;
        this.canClimb = canClimb;
        /*this.canThrowLadders = canThrowLadders;*/
        this.canDigDitch = canDigDitch;
        this.isArab = isArab;
        this.name = name;
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
