package model.Equipment;

public enum EquipmentType {
    //name, hitPoint, engineersCount, damage, range, speed, cost
    PORTABLE_SHIELD("portable shield", 200,1,0,0,0,20),
    BATTERING_RAM("battering ram",300,4,500,1,1,50),
    SIEGE_TOWER("siege tower", 400,4,0,0,0,150),
    CATAPULT("catapult", 600,2,250,12,1,75), //manjenigh moteharek
    TREBUCHET("trebuchet",800,3,400,15,0,100), //manjenigh sabet
    FIRE_BALLISTA("fire ballista",500,2,400,5,2,100)


    ;
    private String name;
    private int hitPoint;
    private int engineerCount;
    private int damage;
    private int range;
    private int speed;
    private int cost;

    EquipmentType(String name, int hitPoint, int engineerCount, int damage, int range, int speed, int cost) {
        this.name = name;
        this.hitPoint = hitPoint;
        this.engineerCount = engineerCount;
        this.damage = damage;
        this.range = range;
        this.speed = speed;
        this.cost = cost;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getRange() {
        return range;
    }

    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getEngineerCount() {
        return engineerCount;
    }

    public int getDamage() {
        return damage;
    }

    public int getSpeed() {
        return speed;
    }

    public static EquipmentType getEquipmentTypeByName(String name){
        for (EquipmentType equipmentType : EquipmentType.values()) {
            if (equipmentType.name.equals(name)){
                return equipmentType;
            }
        }
        return null;
    }

}
