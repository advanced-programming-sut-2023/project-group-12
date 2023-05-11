package controller.GameController;

import model.Building.Building;
import model.Building.BuildingType;
import model.Building.UnitCreation;
import model.Game;
import model.Property.ResourceType;
import model.people.UnitType;
import model.people.soldier.Soldier;

import javax.naming.BinaryRefAddr;

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
        return "this building is repaired!";
    }

    public String createUnit(String type, int count){
        int xPosition = building.getxPosition();
        int yPosition = building.getyPosition();
        boolean horseable = false;
        UnitType unitType = UnitType.getUnitTypeByName(type);
        if (unitType == null) {
            return "there is no unit with the name " + type;
        }
        if(building.getBuildingType() != BuildingType.MERCENARY_POST && building.getBuildingType() != BuildingType.BARRACK && building.getBuildingType() != BuildingType.ENGINEER_GUILD){
            return "this building can not create any units!";
        }
        if (unitType.isArab() && building.getBuildingType() != BuildingType.MERCENARY_POST) {
            return "this building can not create arab units!";
        }
        if(!unitType.isArab() && building.getBuildingType() == BuildingType.MERCENARY_POST){
            return "this building can not create europe units!";
        }
        if (unitType == UnitType.ENGINEER && building.getBuildingType() != BuildingType.ENGINEER_GUILD){
            return "this building can not create engineer units!";
        }
        if(unitType == UnitType.KING || unitType == UnitType.BLACK_MONK){
            return "you can not create this unit!";
        }
        if(building.getOwner().getUnemployed() - count < 50){
            return "you do not have enough people to create units!";
        }
        if(building.getOwner().getGold() < count * unitType.getCost()){
            return "you have not enough gold to create units!";
        }
        if(unitType == UnitType.KNIGHT || unitType == UnitType.HORSE_ARCHER){
            horseable = true;
            if(building.getOwner().getAllHorses() < count){
                return "you have not enough horse to create units!";
            }
        }
        if(building.getOwner().getAllWeaponByWeaponType(unitType.getWeapon()) < count){
            return "you have not enough weapon to create units!";
        }
        if(building.getOwner().getAllDefensiveWeaponByDefenseType(unitType.getDefence()) < count){
            return "you have not enough defensive weapon to create units!";
        }
        for (int i = 0; i < count; i++) {
            Soldier soldier = new Soldier(building.getOwner(), unitType, building.getxPosition(), building.getyPosition());
            newGame.getCurrentMap().getMap()[xPosition][yPosition].addUnits(soldier);
            if( unitType == UnitType.ENGINEER){
                building.getOwner().addEngineer(soldier);
            }
            building.getOwner().addUnit(soldier);
            building.getOwner().addSoldier(soldier);
        }
        building.getOwner().addGold((-1)*count*unitType.getCost());
        building.getOwner().addPopulation((-1)*count);
        //todo horse storage
        building.getOwner().decreaseWeapons(unitType.getWeapon(), count);
        building.getOwner().decreaseDefensiveWeapons(unitType.getDefence(), count);
        //TODO


        return "soldiers created successfully!";
    }
}
