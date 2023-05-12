package model.Building;

import model.Kingdom;

public class Tower extends Building{

    private int fireRange;
    private int defendRange;
    private int hitPoint;

    public Tower(BuildingType buildingType, Kingdom owner, int xPosition, int yPosition) {
        super(buildingType, owner, xPosition, yPosition);
        switch (buildingType) {
            case CIRCLE_TOWER:
            case SQUARE_TOWER:
                fireRange = 3;
                break;
            case PERIMETER_TOWER:
                fireRange = 4;
                break;
            case DEFENCE_TURRET:
                fireRange = 2;
                break;
            case LOOKOUT_TOWER:
                fireRange = 5;
                break;
//            case MAIN_CASTLE:
//                fireRange = 0;
//               break;
        }
    }
}
