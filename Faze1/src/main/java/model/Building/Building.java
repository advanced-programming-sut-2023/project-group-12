package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Building {
    private String name;
    private String buildingType;
    private int xCoordinate;
    private int yCoordinate;
    private int hitPoint;
    private ArrayList<Unit> people;
    private HashMap<String,Integer>price;
    private Kingdom kingdom;
    private int workersNeeded;
    public Building(String name, String buildingType, int xCoordinate, int yCoordinate, int hitPoint, ArrayList<Unit> people, HashMap<String, Integer> price, Kingdom kingdom) {
        this.name = name;
        this.buildingType = buildingType;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.hitPoint = hitPoint;
        this.people = people;
        this.price = price;
        this.kingdom = kingdom;
    }

}
