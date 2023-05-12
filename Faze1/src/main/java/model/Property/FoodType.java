package model.Property;

public enum FoodType {
    BREAD(10),
    MEAT(10),
    CHEESE(10),
    APPLES(10);

    private int buyPrice;

    FoodType(int buyPrice) {
        this.buyPrice = buyPrice;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return buyPrice * 8 / 10;
    }
}
