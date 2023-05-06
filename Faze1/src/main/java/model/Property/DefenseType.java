package model.Property;

public enum DefenseType {
    LEATHER_ARMOR(null, 0),

    METAL_ARMOR(ResourceType.IRON, 1);

    private ResourceType resourceType;
    private int count;
    DefenseType(ResourceType resourceType, int count) {
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
