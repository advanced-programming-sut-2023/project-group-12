package model.Building;

import model.Kingdom;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Wall extends Building{
    private int height;
    private boolean isStaircase;

    public Wall(String name, String buildingType, int xCoordinate, int yCoordinate, int hitPoint, ArrayList<Unit> people, HashMap<String, Integer> price, Kingdom kingdom, int height, boolean isStaircase) {
        super(name, buildingType, xCoordinate, yCoordinate, hitPoint, people, price, kingdom);
        this.height = height;
        this.isStaircase = isStaircase;
    }
}
