package model;

import model.Building.Building;
import model.Building.BuildingType;

import java.util.ArrayList;
import model.Map;
import model.people.Unit;

public class Game {


    private Map currentMap;
    private Building selectedBuilding;
    private ArrayList<User> players;
    private Kingdom currentKingdom;

    private int roundsPassed;

    public void dropBuilding(int x, int y, BuildingType buildingType) {
        Building building = BuildingType.getBuildingByBuildingType(buildingType, currentKingdom, x, y);
        currentMap.getMap()[x][y].setBuilding(building);
    }

    public void selectBuilding(int x, int y) {
        selectedBuilding = currentMap.getMap()[x][y].getBuilding();
//        if(selectedBuilding.getBuildingType().getIsPartOfCastle()){
//            System.out.println(selectedBuilding.getHitPoint());
//        }
    }


    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void selectUnit(int x, int y) {

    }

    public Map getCurrentMap() {
        return  currentMap;
    }

    public Kingdom getCurrentKingdom() {
        return currentKingdom;
    }

    public boolean isEnemyExistsInCell(int x, int y) {
        for(Unit unit : currentMap.getMap()[x][y].units){
            if(unit.getHomeland() != currentKingdom) {
                return true;
            }
        }
        return false;
    }

    public void nextTurn() {

    }


}
