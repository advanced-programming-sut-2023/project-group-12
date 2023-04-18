package model.Building;

public class Storage extends Building{
    private int capacity;

    public Storage(StorageType type) {
        this.capacity = type.getCapacity();
        super.costs.put(type.getPriceType(),type.getPrice());
        super.hitPoint = type.getHitPoint();
        super.initialHitPoint = type.getHitPoint();
    }

}
