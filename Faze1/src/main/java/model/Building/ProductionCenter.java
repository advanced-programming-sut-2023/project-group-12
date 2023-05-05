package model.Building;

import model.Kingdom;

public class ProductionCenter extends Building{
    private int productionRate;
    private int hitPoint;
    private String productType;
    private int usageRate;
    private String sourceType;

    public ProductionCenter(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        hitPoint = buildingType.getHitPoint();
    }


    public void useSource () {

    }
    public void produce () {

    }
}
