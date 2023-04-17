package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage extends Building{
    private int capacity;
    private String storedType;

    public Storage(String name, String buildingType, int xCoordinate, int yCoordinate, int hitPoint, ArrayList<Unit> people, HashMap<String, Integer> price, Kingdom kingdom, int capacity, String storedType) {
        super(name, buildingType, xCoordinate, yCoordinate, hitPoint, people, price, kingdom);
        this.capacity = capacity;
        this.storedType = storedType;
    }
    public boolean isFull () {
        return false;
    }
}
