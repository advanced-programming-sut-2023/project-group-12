package model.Building;

public class Tower extends Building{
    private int fireRange;
    private int defendRange;
    private int soldierCapacity;

    public Tower(TowerType type) {
        this.fireRange = type.getFireRange();
        this.defendRange = type.getDefendRange();
        super.hitPoint = type.getHitPoint();
        super.initialHitPoint = type.getHitPoint();
        super.costs.put(type.getPriceType(),type.getPrice());
        this.soldierCapacity = type.getSoldierCapacity();
    }

}
