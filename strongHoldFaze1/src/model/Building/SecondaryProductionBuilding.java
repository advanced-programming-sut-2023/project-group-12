package model.Building;

import java.util.ArrayList;

public class SecondaryProductionBuilding extends Building{
    private static ArrayList<SecondaryProductionBuilding> buildings;
    static {
        buildings = new ArrayList<>();
    }
    private int productionRate;
    private int usageRate;
    private String product;
    private String source;

    public SecondaryProductionBuilding(SecondaryProductionType type) {
        this.product = type.getProduct();
        this.source = type.getSource();
        this.productionRate = type.getProductionRate();
        this.usageRate = type.getUsageRate();
        super.costs.put(type.getPriceType(), type.getPrice());
        super.hitPoint = type.getHitPoint();
        super.initialHitPoint = type.getHitPoint();
    }
}
