package model.Building;

import model.Kingdom;

public class Gate extends Building{

    private boolean isOpen;
    public Gate(BuildingType buildingType, Kingdom owner) {
        super(buildingType, owner);
        isOpen = false;
    }

    public void changeDoorStatus(){
        isOpen = !isOpen;
    }

    public boolean isDoorOpen(){
        return isOpen;
    }
}
