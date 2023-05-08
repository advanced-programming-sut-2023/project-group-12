package controller.GameController;

import model.Building.BuildingType;
import model.Game;

public class GameMenuController {
    private Game newGame;

    public String dropBuilding(int x, int y, String type) {
        BuildingType buildingType = BuildingType.getBuildingTypeByName(type);
        if(buildingType == null){
            return "building name is not correct";
        }
        if(x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()){
            return "your coordinates are not correct";
        }
        if(!BuildingType.isBuildingMatchTexture(buildingType, newGame.getCurrentMap().getMap()[x][y].getTextureType())){
            return "invalid texture!";
        }
        newGame.dropBuilding(x, y, buildingType);
        return "make building successfully!";
    }

    public String selectBuilding(int x, int y) {
        if(x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()){
            return "your coordinates are not correct";
        }
        if(newGame.getCurrentMap().getMap()[x][y].getBuilding() == null){
            return "there is not any building here!";
        }
        newGame.selectBuilding(x, y);
        return "building selected successfully!";
    }

    public String createUnit(int x, int y) {
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
