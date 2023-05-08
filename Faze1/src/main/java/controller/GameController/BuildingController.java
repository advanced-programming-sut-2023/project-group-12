package controller.GameController;

import model.Building.Building;
import model.Game;
import model.Property.ResourceType;

public class BuildingController {
    private Building building;

    private Game newGame;

    public String repair(Building building){
        if(!building.getBuildingType().getIsPartOfCastle()){
            return "this building is not part of the castle!";
        }
        if(building.getHitPoint() == building.getBuildingType().getHitPoint()){
            return "full HP!";
        }
        int cost = ((building.getBuildingType().getHitPoint() - building.getHitPoint())/building.getBuildingType().getHitPoint())*building.getBuildingType().getResourceCount();
        int balance = building.getOwner().getAllResources().get(ResourceType.STONE);
        if( balance < cost){
            return "you do not have enough stones!";
        }
        building.getOwner().getAllResources().replace(ResourceType.STONE, balance - cost);
        building.resetHealth();
        newGame.repairBuilding();
        return "";
    }
}
