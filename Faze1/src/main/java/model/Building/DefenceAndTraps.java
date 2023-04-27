package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class DefenceAndTraps extends Building{
    private int fireRange;
    private int defendRange;
    private int damage;
    private int capacity;
    private boolean isDamagedByTunnel;
    private ArrayList<Unit> units;

    public DefenceAndTraps(String name, String buildingType, int xCoordinate, int yCoordinate, int hitPoint, ArrayList<Unit> people, HashMap<String,
            Integer> price, Kingdom kingdom, int fireRange, int defendRange, int damage, ArrayList<Unit> units, int capacity, boolean isDamagedByTunnel) {
        super(name, buildingType, xCoordinate, yCoordinate, hitPoint, people, price, kingdom);
        this.fireRange = fireRange;
        this.defendRange = defendRange;
        this.damage = damage;
        this.units = units;
        this.capacity = capacity;
        this.isDamagedByTunnel = isDamagedByTunnel;
    }

    public void helpDefence () {

    }

}
