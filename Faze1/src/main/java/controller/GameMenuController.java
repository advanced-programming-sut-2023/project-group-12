package controller;

import model.Building.BuildingType;
import model.Game;

public class GameMenuController {
    private Game newGame;

    public String dropBuilding(int x, int y, String type) {
        BuildingType building = BuildingType.getBuildingTypeByName(type);
        if(building == null){
            return "building name is not correct";
        }
        if(x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()){
            return "your coordinates are not correct";
        }
        //todo: check cell structure
        newGame.dropBuilding(x, y, building);
        return "make building successfully!";
    }

    public String selectBuilding(int x, int y) {
        if(x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()){
            return "your coordinates are not correct";
        }
        newGame.selectBuilding(x, y);
        return "building selected successfully!";
    }

    public String createUnit(int x, int y) {
        return "";
    }

    public String Repair(){
        if(newGame.getSelectedBuilding() == null){
            return  "No buildings have been selected!";
        }
        //todo: complete repair

        newGame.repairBuilding();
        return "";
    }
    public String chooseColor(String color) {
        return "";
    }
    public String changeColor (String color) {
        return "";
    }
    public String chooseKeep (int keepNumber) {
        return "";
    }
    public String changeKeep (int keepNumber) {
        return "";
    }
    public String dropUnit (int x, int y, String type, int count) {
        return "";
    }
}
