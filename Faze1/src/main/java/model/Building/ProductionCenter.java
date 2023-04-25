package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductionCenter extends Building{
    private int productionRate;
    private String productType;
    private int usageRate;
    private String sourceType;

    public ProductionCenter(String name, String buildingType, int xCoordinate, int yCoordinate, int hitPoint, ArrayList<Unit> people, HashMap<String, Integer> price, Kingdom kingdom, int productionRate, String productType, int usageRate, String sourceType) {
        super(name, buildingType, xCoordinate, yCoordinate, hitPoint, people, price, kingdom);
        this.productionRate = productionRate;
        this.productType = productType;
        this.usageRate = usageRate;
        this.sourceType = sourceType;
    }
    public void useSource () {

    }
    public void produce () {

    }
}
