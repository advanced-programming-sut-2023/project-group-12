package model.Building;

import java.util.ArrayList;

public class PrimaryProductionBuilding extends Building{
    private static ArrayList<PrimaryProductionBuilding> buildings;
    static {
        buildings = new ArrayList<>();
    }
    private int productionRate;
    private String product;

    public PrimaryProductionBuilding(PrimaryProductionType type) {
        this.productionRate = type.getRate();
        this.product = type.getProduct();
        super.costs.put(type.getPriceType(),type.getPrice());
        super.hitPoint = type.getHitPoint();
        super.initialHitPoint = type.getHitPoint();
    }
    public void changeProductAmount () {

    }
}
