package model.Building;

public enum SecondaryProductionType {
    BREWERY(1,1,"BARLEY","BEAR",0,"wood",0,0),
    BAKERY(1,1,"bread", "wheat",0,"wood",0,0),
    POLE_TURNER(1,1,"bread", "wheat",0,"wood",0,0),
    FLETCHER(1,1,"bread", "wheat",0,"wood",0,0),
    BLACKSMITH(1,1,"bread", "wheat",0,"wood",0,0),
    ARMOURER(1,1,"bread", "wheat",0,"wood",0,0),
    MILL(1,1,"bread", "wheat",0,"wood",0,0),
    INN(1,1,"bread", "wheat",0,"wood",0,0);
    private int productionRate;
    private int usageRate;
    private String product;
    private String source;
    private int price;
    private String priceType;
    private int hitPoint;
    private int workersNeeded;
    private String texture;

    public String getTexture() {
        return texture;
    }

    public int getWorkersNeeded() {
        return workersNeeded;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public int getProductionRate() {
        return productionRate;
    }

    public int getUsageRate() {
        return usageRate;
    }

    public String getProduct() {
        return product;
    }

    public String getSource() {
        return source;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceType() {
        return priceType;
    }

    private SecondaryProductionType(int productionRate, int usageRate, String product, String source, int price, String priceType, int hitPoint, int workersNeeded) {
        this.productionRate = productionRate;
        this.usageRate = usageRate;
        this.product = product;
        this.source = source;
        this.price = price;
        this.priceType = priceType;
        this.hitPoint = hitPoint;
        this.workersNeeded = workersNeeded;
    }
}
