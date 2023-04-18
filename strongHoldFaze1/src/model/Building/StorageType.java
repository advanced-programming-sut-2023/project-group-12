package model.Building;

public enum StorageType {
    MATERIAL_STORAGE(0,"wood",0,0),
    FOOD_STORAGE(0,"wood",0,0),
    ARMOURY(0,"wood",0,0);

    public int getPrice() {
        return price;
    }

    public String getPriceType() {
        return priceType;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    private int price;
    private String priceType;
    private int capacity;
    private int hitPoint;
    private String texture;

    public String getTexture() {
        return texture;
    }

    StorageType(int price, String priceType, int capacity, int hitPoint) {
        this.price = price;
        this.priceType = priceType;
        this.capacity = capacity;
        this.hitPoint = hitPoint;
    }
}
