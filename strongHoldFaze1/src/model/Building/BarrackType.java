package model.Building;

public enum BarrackType {
    BARRACK,
    MERCENARY_POST,
    ENGINEER_GUILD;
    private int price;
    private String priceType;
    private int capacity;
    private String texture;

    public int getPrice() {
        return price;
    }

    public String getPriceType() {
        return priceType;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getTexture() {
        return texture;
    }
}
