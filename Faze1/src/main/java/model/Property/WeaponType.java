package model.Property;

public enum WeaponType {
    BOW(ResourceType.WOOD, 2),
    CROSS_BOW(ResourceType.WOOD, 3),
    SPEAR(ResourceType.WOOD, 1),
    PIKE(ResourceType.WOOD, 2),
    MACE(ResourceType.IRON, 1),
    SWORD(ResourceType.IRON, 1),

    PETROLEUM(null, 0)

    ;

    private ResourceType resourceType;
    private int count;

    WeaponType(ResourceType resourceType, int count) {
        this.resourceType = resourceType;
        this.count = count;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public int getCount() {
        return count;
    }
}
