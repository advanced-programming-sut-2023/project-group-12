package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class UnitCreation extends Building{
    private int rate;
    private String unitType;

    public UnitCreation(String name, String buildingType, int xCoordinate, int yCoordinate, int hitPoint, ArrayList<Unit> people, HashMap<String, Integer> price, Kingdom kingdom, int rate, String unitType) {
        super(name, buildingType, xCoordinate, yCoordinate, hitPoint, people, price, kingdom);
        this.rate = rate;
        this.unitType = unitType;
    }
    public void createUnit() {

    }

}
