package model.Property;

public enum WeaponType {
    BOW(ResourceType.WOOD, 2, 20),
    CROSS_BOW(ResourceType.WOOD, 3, 20),
    SPEAR(ResourceType.WOOD, 1, 20),
    PIKE(ResourceType.WOOD, 2, 20),
    MACE(ResourceType.IRON, 1, 30),
    SWORD(ResourceType.IRON, 1, 30),
    PETROLEUM(null, 0, 10);


    private final ResourceType resourceType;
    private final int count;

    private final int buyPrice;

    WeaponType(ResourceType resourceType, int count, int buyPrice) {
        this.resourceType = resourceType;
        this.count = count;
        this.buyPrice = buyPrice;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getCount() {
        return count;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return buyPrice * 8 / 10;
    }
}
