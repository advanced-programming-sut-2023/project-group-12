package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class UnitCreation extends Building{
    private int rate;
    private String unitType;

    public UnitCreation(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
    }


    public void createUnit() {

    }

}
