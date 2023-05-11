package model.Building;

import model.Kingdom;
import model.Property.*;

import java.net.Authenticator;
import java.util.ArrayList;
import java.util.HashMap;

public class Storage extends Building{
    private BuildingType buildingType;

    private int capacity;

    private ArrayList<Property> balance = new ArrayList<>() ;

    public Storage(BuildingType buildingType, Kingdom owner, int xPosition, int yPosition) {
        super(buildingType, owner, xPosition, yPosition);
        switch (buildingType) {
            case ARMOURY:
                capacity = 50;
                balance.add(new DefensiveWeapon(DefenseType.LEATHER_ARMOR, 1));
                balance.add(new DefensiveWeapon(DefenseType.METAL_ARMOR, 1));
                break;
            case STOCKPILE:
                capacity = 1000;
                for (ResourceType type : ResourceType.values()) {
                    balance.add(new Resources(type, 1));
                }
                break;
            case FOOD_STOCKPILE:
                capacity = 1000;
                for (FoodType type : FoodType.values()) {
                    balance.add(new Food(type, 1));
                }
                break;
            default:
                break;
        }
    }


    public boolean isFull () {
        int amount = 0;
        for(int i=0; i<balance.size(); i++) {
            amount += balance.get(i).getValue();
        }
        return amount == capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getStored() {
        int amount = 0;
        for(int i=0; i<balance.size(); i++) {
            amount += balance.get(i).getValue();
        }
        return amount ;
    }

    public ArrayList<Property> getBalance() {
        return balance;
    }






}
