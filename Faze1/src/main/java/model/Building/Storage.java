package model.Building;

import model.Kingdom;
import model.Property.DefensiveWeapon;
import model.Property.Property;

public class Storage extends Building{
    private BuildingType buildingType;
    private int capacity;
    private Property storedType;
    private int hitPoint;

    public Storage(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        hitPoint = buildingType.getHitPoint();
        switch (buildingType) {
            case ARMOURY:
//                storedType = new DefensiveWeapon();
                break;
            case STOCKPILE:
                break;
            case FOOD_STOCKPILE:
                break;
            default:
                break;
        }
    }


    public boolean isFull () {
        return false;
    }
}
