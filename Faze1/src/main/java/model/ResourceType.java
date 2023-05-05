package model;

public enum ResourceType {
    WHEAT("wheat",3,4),
    FLOUR("flour",4,6),
    HOPS("hops",5,8),
    ALE("ale",7,10),
    STONE("stone",10,16),
    IRON("iron",14,20),
    WOOD("wood",8,12),
    PITCH("pitch",11,15);
    private String name;
    private int sellPrice;
    private int buyPrice;


    ResourceType(String name, int sellPrice, int buyPrice) {
        this.name = name;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
    }

    public String getName() {
        return name;
    }

    public int getBuyPrice() {
        return buyPrice;
    }

    public int getSellPrice() {
        return sellPrice;
    }

    public static ResourceType getResourcesTypeByString(String name) {
        for(ResourceType resource : ResourceType.values()) {
            if (resource.name.equals(name)) return resource;
        }
        return null;
    }
}
