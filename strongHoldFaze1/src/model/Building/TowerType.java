package model.Building;

public enum TowerType {
    LOOKOUT_TOWER(0,0,0,0,"gold",0),
    ROUND_TOWER(0,0,0,0,"gold",0),
    DEFENCE_TOWER(0,0,0,0,"gold",0),
    SQUARE_TOWER(0,0,0,0,"gold",0),
    CIRCLE_TOWER(0,0,0,0,"gold",0);
    private String texture;

    public String getTexture() {
        return texture;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getFireRange() {
        return fireRange;
    }

    public int getDefendRange() {
        return defendRange;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceType() {
        return priceType;
    }

    private int hitPoint;
    private int fireRange;
    private int defendRange;
    private int price;
    private String priceType;
    private int soldierCapacity;

    public int getSoldierCapacity() {
        return soldierCapacity;
    }

    TowerType(int hitPoint, int fireRange, int defendRange, int price, String priceType, int soldierCapacity) {
        this.hitPoint = hitPoint;
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.price = price;
        this.priceType = priceType;
        this.soldierCapacity = soldierCapacity;
    }
}
