package model.Building;

import model.Kingdom;
import model.people.Unit;
import model.people.UnitType;

import java.util.HashMap;

public class UnitCreation extends Building{
    private int rate;
    private HashMap<UnitType, Integer> allUnit = new HashMap<>() {

    };

    public UnitCreation(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);

        switch (buildingType){
            case BARRACK:
                for(UnitType type : UnitType.values()){
                    if(!type.isArab()){
                        allUnit.put(type, 0);
                    }
                }
                break;
            case MERCENARY_POST:
                for(UnitType type : UnitType.values()){
                    if(type.isArab()){
                        allUnit.put(type, 0);
                    }
                }
                break;
            case HOVEL:
                break;
            case CHURCH:
                break;
            case CATHEDRAL:
                break;
        }
    }


    public void createUnit() {

    }

}
