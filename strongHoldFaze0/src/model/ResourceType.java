package model;

public enum ResourceType {
    ARMOUR_COST(0),
    MACE_COST(0),
    SWORD_COST(0),
    SPEAR_COST(0),
    ENGINEER_COST(0),
    WOOD_COST(0),
    STONE_COST(0),
    IRON_COST(0);

    private int price;

    ResourceType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
