package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;

public class Traps extends Building{
    private int damage;
    private int capacity;
    private boolean isDamagedByTunnel;
    private ArrayList<Unit> units;

    public Traps(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
    }


    public void helpDefence () {

    }

}
