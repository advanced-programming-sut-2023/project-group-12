package controller.GameController;

import model.Building.Building;
import model.Building.BuildingType;
import model.Game;

import javax.swing.plaf.PanelUI;

public class GameMenuController {
    private Game newGame;
    private KingdomController kingdomController = new KingdomController();
    private BuildingController buildingController = new BuildingController();

    public String dropBuilding(String X, String Y, String type) {
        if (X.isEmpty()) {
            return "X can't be empty";
        }
        if (Y.isEmpty()) {
            return "Y can't be empty";
        }
        int x, y;
        try {
            x = Integer.parseInt(X);
        } catch (NumberFormatException e) {
            return "X must be a number";
        }
        try {
            y = Integer.parseInt(Y);
        } catch (NumberFormatException e) {
            return "Y must be a number";
        }
        BuildingType buildingType = BuildingType.getBuildingTypeByName(type);
        if (buildingType == null) {
            return "building name is not correct";
        }
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (!BuildingType.isBuildingMatchTexture(buildingType, newGame.getCurrentMap().getMap()[x][y].getTextureType)) {
            return "invalid texture!";
        }
        if (newGame.isEnemyExistsInCell(x, y)) {
            return "there is already an enemy in this cell and you cannot drop it!";
        }
        newGame.dropBuilding(x, y, buildingType);
        return "make building successfully!";
    }

    public String selectBuilding(String X, String Y) {// todo: maybe fix the duplicate code
        if (X.isEmpty()) {
            return "X can't be empty";
        }
        if (Y.isEmpty()) {
            return "Y can't be empty";
        }
        int x, y;
        try {
            x = Integer.parseInt(X);
        } catch (NumberFormatException e) {
            return "X must be a number";
        }
        try {
            y = Integer.parseInt(Y);
        } catch (NumberFormatException e) {
            return "Y must be a number";
        }
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (newGame.getCurrentMap().getMap()[x][y].getBuilding() == null) {
            return "there is not any building here!";
        }
        newGame.selectBuilding(x, y);
        return "building selected successfully!";
    }

    public String createUnit(String type, String count) {
        if (type.isEmpty()) {
            return "Type can't be empty";
        }
        if (count.isEmpty()) {
            return "Count can't be empty";
        }
        int number;
        try {
            number = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            return "Count must be a number";
        }
        if (number <= 0) {
            return "Count must be positive";
        }
        return buildingController.createUnit(type, number);
    }

    // todo : for Mahdi
    public String chooseColor(String color) {
        //todo: what do we do with this?
        String playerColor;
        try {
            color = color.toLowerCase();
        } catch (NullPointerException e) {
            return "color can't be empty";
        }
        return "";
    }

    public String changeColor(String color) {
        return "";
    }

    public String chooseKeep(String keepNumber) {
        return "";
    }

    public String changeKeep(String keepNumber) {
        return "";
    }

    //
    public String dropUnit(String X, String Y, String type, String count) {
        if (type.isEmpty()) {
            return "Type can't be empty";
        }
        if (count.isEmpty()) {
            return "Count can't be empty";
        }
        int number;
        try {
            number = Integer.parseInt(count);
        } catch (NumberFormatException e) {
            return "Count must be a number";
        }
        if (number <= 0) {
            return "Count must be positive";
        }
        if (X.isEmpty()) {
            return "X can't be empty";
        }
        if (Y.isEmpty()) {
            return "Y can't be empty";
        }
        int x, y;
        try {
            x = Integer.parseInt(X);
        } catch (NumberFormatException e) {
            return "X must be a number";
        }
        try {
            y = Integer.parseInt(Y);
        } catch (NumberFormatException e) {
            return "Y must be a number";
        }
        //todo : return the result of dropUnit
        return "";
    }

    public String setFoodRate(String number) throws NumberFormatException {
        return kingdomController.setFoodRate(number);
    }

    public String setTaxRate(String number) throws NumberFormatException {
        return kingdomController.setTaxRate(number);
    }

    public String showTaxRate() {
        return kingdomController.showTaxRate();
    }

    public String setFearRate(String number) throws NumberFormatException {
        return kingdomController.setFearRate(number);
    }

    public String repair() {
        return buildingController.repair(newGame.getSelectedBuilding());
    }

    public String showFoodRate() {
        return kingdomController.showFoodRate();
    }

    public String showFoodList() {
        return kingdomController.showFoodList();
    }

    public String showPopularity() {
        return kingdomController.showPopularity();
    }

    public String showPopularityFactors() {
        return kingdomController.showPopularityFactors();
    }

    public String selectUnit(String xCoordinate, String yCoordinate) {
        
    }

    public String moveUnit(String xCoordinate, String yCoordinate) {
        
    }

    public String patrolUnit(String x1Coordinate, String y1Coordinate, String x2Coordinate, String y2Coordinate) {
    }

    public String setMode(String xCoordinate, String yCoordinate, String mode) {
    }

    public String disbandUnit() {
    }

    public String buildEquipment(String equipmentName) {
    }

    public String digTunnel(String xCoordinate, String yCoordinate) {
    }

    public String pourOil(String direction) {
    }

    public String airAttack(String xCoordinate, String yCoordinate) {
    }

    public String groundAttack(String xCoordinate, String yCoordinate) {
    }
}
