package model.Building;

public enum GateType {
    SMALL_STONE_GATEHOUSE(0,0,"gold",0,'c',"grass"),
    BIG_STONE_GATEHOUSE(0,0,"gold",0,'c',"grass");
    private int hitPoint;
    private int price;
    private String priceType;
    private int peopleCapacity;
    private char direction;
    private String texture;

    public int getHitPoint() {
        return hitPoint;
    }

    public int getPrice() {
        return price;
    }

    public String getPriceType() {
        return priceType;
    }

    public int getPeopleCapacity() {
        return peopleCapacity;
    }

    public char getDirection() {
        return direction;
    }

    public String getTexture() {
        return texture;
    }

    GateType(int hitPoint, int price, String priceType, int peopleCapacity, char direction, String texture) {
        this.hitPoint = hitPoint;
        this.price = price;
        this.priceType = priceType;
        this.peopleCapacity = peopleCapacity;
        this.direction = direction;
        this.texture = texture;
    }
}
