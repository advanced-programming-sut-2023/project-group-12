package model.Property;

public enum DefenseType {
    LEATHER_ARMOR(null, 0, 5),

    METAL_ARMOR(ResourceType.IRON, 1, 10);

    private ResourceType resourceType;
    private int count;

    private int buyPrice;
    DefenseType(ResourceType resourceType, int count, int buyPrice) {
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
