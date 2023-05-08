package model;

import model.Building.Building;
import model.Property.ResourceType;
import model.Property.Resources;
import model.people.Unit;

import java.util.ArrayList;
import java.util.HashMap;

public class Kingdom {

    private ArrayList<Unit> people;
    private ArrayList<Building>buildings;
    private User owner;

    private int gold;
    private int fearRate;
    private int texRate;
    private int foodRate;
    private HashMap<ResourceType,Integer> allResources;

    {
        allResources = new HashMap<>();
        allResources.put(ResourceType.BARELY,0);
        allResources.put(ResourceType.WOOD,0);
        allResources.put(ResourceType.IRON,0);
        allResources.put(ResourceType.ALE,0);
        allResources.put(ResourceType.FLOUR,0);
        allResources.put(ResourceType.PITCH,0);
        allResources.put(ResourceType.WHEAT,0);
    }

    public void setPopularity(int popularity) {

    }

    public int getFearRate() {
        return fearRate;
    }

    public void setFearRate(int fearRate) {
        this.fearRate = fearRate;
    }

    public int getTaxRate() {
        return texRate;
    }

    public void setTaxRate(int texRate) {
        this.texRate = texRate;
    }

    public int getFoodRate() {
        return foodRate;
    }

    public void setFoodRate(int foodRate) {
        this.foodRate = foodRate;
    }

    public int getGold() {
        return gold;
    }

    public User getOwner() {
        return owner;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }


    public HashMap<ResourceType, Integer> getAllResources() {
        return allResources;
    }
}
