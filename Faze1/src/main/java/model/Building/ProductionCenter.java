package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

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
