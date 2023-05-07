package model.Building;

import model.Kingdom;
import model.Property.*;

import java.net.Authenticator;
import java.util.HashMap;

public class Storage extends Building{
    private BuildingType buildingType;
    private int capacity;

    private HashMap<Property, Integer> balance = new HashMap<>() ;
    private int hitPoint;

    public Storage(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        hitPoint = buildingType.getHitPoint();
        switch (buildingType) {
            case ARMOURY:
                capacity = 50;
                balance.put(new DefensiveWeapon(DefenseType.LEATHER_ARMOR, 1), 0);
                balance.put(new DefensiveWeapon(DefenseType.METAL_ARMOR, 1), 0);
                break;
            case STOCKPILE:
                capacity = 1000;
                for (ResourceType type : ResourceType.values()) {
                    balance.put(new Resources(type, 1), 0);
                }
                break;
            case FOOD_STOCKPILE:
                capacity = 1000;
                for (FoodType type : FoodType.values()) {
                    balance.put(new Food(type, 1), 0);
                }
                break;
            default:
                break;
        }
    }


    public boolean isFull () {
        int amount = 0;
        for(int i=0; i<balance.size(); i++) {
            amount += balance.get(i);
        }
        return amount == capacity;
    }
}
