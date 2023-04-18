package model.Building;

public enum PrimaryProductionType {
    IRON_MINE(1, "iron",0,"wood",0,0),
    PITCH_RIG(1, "iron",0,"wood",0,0),
    QUARRY(1, "iron",0,"wood",0,0),
    WOODCUTTER(1, "iron",0,"wood",0,0),
    STABLE(1, "iron",0,"wood",0,0),
    DAIRY_PRODUCTS(1, "iron",0,"wood",0,0),
    APPLE_GARDEN(1, "iron",0,"wood",0,0),
    ZOB_FACTORY(1,"MOZAB",0,"wood",0,0),
    WHEAT_FARM(1,"WHEAT",0,"wood",0,0),
    BARLEY_FARM(1,"BARLEY",0,"wood",0,0),
    HUNTING_POST(1, "iron",0,"wood",0,0),
    OX_TETHER(1, "iron",0,"wood",0,0);

    public int getPrice() {
        return price;
    }

    public String getPriceType() {
        return priceType;
    }

    private int rate;
    private String product;
    private int price;
    private String priceType;
    private int hitPoint;
    private int workersNeeded;

    public int getWorkersNeeded() {
        return workersNeeded;
    }
    private String texture;

    public String getTexture() {
        return texture;
    }

    private PrimaryProductionType(int rate, String product, int price, String priceType, int hitPoint, int workersNeeded) {
        this.rate = rate;
        this.product = product;
        this.price = price;
        this.priceType = priceType;
        this.hitPoint = hitPoint;
        this.workersNeeded = workersNeeded;
    }

    public int getRate() {
        return rate;
    }

    public String getProduct() {
        return product;
    }

    public int getHitPoint() {
        return hitPoint;
    }
}
