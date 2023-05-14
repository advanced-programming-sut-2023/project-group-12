package controller.GameController;

import model.Building.Building;
import model.Building.BuildingType;
import model.Equipment.Equipment;
import model.Equipment.EquipmentType;
import model.Game;
import model.Kingdom;
import model.Property.WeaponType;
import model.User;
import model.UserDatabase;
import model.map.Cell;
import model.people.Unit;
import model.people.UnitType;

import java.util.ArrayList;

public class GameMenuController {
    //todo : I think for move and fight we should have some units set to do that thing and let them do it at the end
    public GameMenuController(Game newGame) {
        this.newGame = newGame;
        this.buildingController = new BuildingController(newGame);
        this.kingdomController = new KingdomController(newGame.getCurrentKingdom());// todo : is it okay ?
    }

    private Game newGame;
    public void buildKingdoms () {
        for (User user : UserDatabase.getUsers()) {
            Kingdom kingdom = new Kingdom(user);
            newGame.getKingdoms().add(kingdom);
        }
    }
    public void setCurrentKingdom (User user) {
        for (Kingdom kingdom : newGame.getKingdoms()) {
            if (kingdom.getOwner().equals(user)) {
                newGame.setCurrentKingdom(kingdom);
                return;
            }
        }
    }
    public void setNewGame(Game newGame) {
        this.newGame = newGame;
    }

    private KingdomController kingdomController;
    private BuildingController buildingController;

    public Game getNewGame() {
        return newGame;
    }

    public String dropBuilding(String X, String Y, String type) {
        String output = checkNumber(X);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(Y);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(X);
        int y = Integer.parseInt(Y);
        if (type.isEmpty()) {
            return "type can't be empty";
        }
        BuildingType buildingType = BuildingType.getBuildingTypeByName(type);
        if (buildingType == null) {
            return "building name is not correct";
        }
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (!BuildingType.isBuildingMatchTexture(buildingType, newGame.getCurrentMap().getMap()[x][y].getTextureType())) {
            return "invalid texture!";
        }
        if (newGame.isEnemyExistsInCell(x, y)) {
            return "there is already an enemy in this cell and you cannot drop it!";
        }
        int check = 0;
        if (buildingType == BuildingType.STOCKPILE) {
            ArrayList<Cell> neighbors = newGame.neighbors(x, y);
            for (Cell cell : neighbors) {
                if (cell.getBuilding().getBuildingType() == buildingType) {
                    check = 1;
                    break;
                }
            }
            if (check == 0) {
                return "you should build stockpile near your other stockpiles!";
            }
        }
        newGame.dropBuilding(x, y, buildingType);
        return "make building successfully!";
    }

    public String selectBuilding(String X, String Y) {// todo: maybe fix the duplicate code
        String output = checkNumber(X);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(Y);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(X);
        int y = Integer.parseInt(Y);
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (newGame.getCurrentMap().getMap()[x][y].getBuilding() == null) {
            return "there is not any building here!";
        }
        newGame.selectBuilding(x, y);
        BuildingController.building = newGame.getSelectedBuilding();
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
        String output = checkNumber(X);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(Y);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(X);
        int y = Integer.parseInt(Y);
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
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (UnitType.getUnitTypeByName(type) == null) {
            return "unit name is not correct";
        }
        for (int i = 0; i < number; i++) {
            Unit unit = new Unit(newGame.getCurrentKingdom(),UnitType.getUnitTypeByName(type),x,y);
            unit.setxPosition(x);
            unit.setyPosition(y);
            newGame.getCurrentMap().getMap()[x][y].addUnits(unit);
            newGame.getCurrentKingdom().addUnit(unit);
        }
        return "units dropped successfully!";
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

    public String selectUnit(String xCoordinate, String yCoordinate, String type) {
        String output = checkNumber(xCoordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(yCoordinate);
        if (!output.equals("")) {
            return output;
        }
        if (type.isEmpty()) {
            return "type can't be empty";
        }
// todo : check if type is correct
        int x = Integer.parseInt(xCoordinate);
        int y = Integer.parseInt(yCoordinate);
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        newGame.clearSelectedUnits();
        int selected = 0, yours = 0;
        if (newGame.getCurrentMap().getMap()[x][y].getUnits() == null) {
            return "there's no unit here!";
        }
        for (Unit unit : newGame.getCurrentMap().getMap()[x][y].getUnits()) {
            if (unit.getHomeland() == newGame.getCurrentKingdom()) {
                if (unit.getUnitType() == UnitType.getUnitTypeByName(type)) {
                    newGame.setSelectedUnits(unit);
                    selected++;
                }
                yours++;
            }
        }
        if (yours == 0) {
            return "None of your units are here";
        }
        if (selected == 0) {
            return "There's no unit of this type here";
        }
        return "units selected successfully!";
    }

    public String moveUnit(String xCoordinate, String yCoordinate) {
        String output = checkNumber(xCoordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(yCoordinate);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(xCoordinate);
        int y = Integer.parseInt(yCoordinate);
        if (x < 0 || y < 0 || x > newGame.getCurrentMap().getDimension() || y > newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (!newGame.getCurrentMap().getMap()[x][y].isPassable()) {
            return "the destination is not a valid destination, troops can't be there";
        }
        if (newGame.getSelectedUnits() == null || newGame.getSelectedUnits().size() == 0) {
            return "there's no unit selected";
        }
        if (newGame.getSelectedUnits().get(0).getSpeed() == 0) {
            return "this unit can't move";
        }
        return newGame.moveUnit(newGame.getSelectedUnits().get(0).getxPosition(), newGame.getSelectedUnits().get(0).getxPosition(), x, y, newGame.getSelectedUnits());// todo : some how give me the current coordinates
    }

    public String patrolUnit(String x1Coordinate, String y1Coordinate, String x2Coordinate, String y2Coordinate) {
        String output = checkNumber(x1Coordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(y1Coordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(x2Coordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(y2Coordinate);
        if (!output.equals("")) {
            return output;
        }
        int x1 = Integer.parseInt(x1Coordinate);
        int y1 = Integer.parseInt(y1Coordinate);
        int x2 = Integer.parseInt(x2Coordinate);
        int y2 = Integer.parseInt(y2Coordinate);
        if (x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x1 >= newGame.getCurrentMap().getDimension() || y1 >= newGame.getCurrentMap().getDimension() || x2 >= newGame.getCurrentMap().getDimension() || y2 >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (x1 == x2 && y1 == y2) {
            return "you can't patrol a unit in one place";
        }
        if (!isUnitSelected().equals("true")) {
            return isUnitSelected();
        }
        if (newGame.getSelectedUnits().get(0).getSpeed() == 0) {
            return "this unit can't move";
        }
        if (!newGame.getCurrentMap().getMap()[x1][y1].isPassable()) {
            return "the end point is not a valid destination, troops can't be there";
        }
        if (!newGame.getCurrentMap().getMap()[x2][y2].isPassable()) {
            return "the start point is not a valid destination, troops can't be there";
        }
        newGame.getPatrollingUnits().clear();
        newGame.getPatrollingUnits().addAll(newGame.getSelectedUnits());
        newGame.patrolUnit(x1, y1, x2, y2);
        return "patrolling started successfully";
    }

    public String setMode(String xCoordinate, String yCoordinate, String mode, String type) {
        String output = checkNumber(xCoordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(yCoordinate);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(xCoordinate);
        int y = Integer.parseInt(yCoordinate);
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (mode.isEmpty()) {
            return "mode can't be empty";
        }
        if (!mode.equals("offensive") && !mode.equals("defensive") && !mode.equals("standing")) {
            return "mode is not correct";
        }
        if (type.isEmpty()) {
            return "type can't be empty";
        }
        if (newGame.getCurrentMap().getMap()[x][y].getUnits() == null) {
            return "there's no unit here!";
        }
        int thisTypeUnit = 0;
        for (Unit unit : newGame.getCurrentMap().getMap()[x][y].getUnits()) {
            if (unit.getUnitType() == UnitType.getUnitTypeByName(type)) {
                unit.setMode(mode);
                thisTypeUnit++;
            }
        }
        if (thisTypeUnit == 0) {
            return "There's no unit of this type here";
        }
        return "mode set successfully!";
    }

    // todo: disband is not done yet
    public String disbandUnit() {
        if (newGame.getSelectedUnits().size() == 0 || newGame.getSelectedUnits() == null) {
            return "no unit to disband";
        }
        //moveUnit(newGame.getSelectedUnits().get(0).getxPosition(), newGame.getSelectedUnits().get(0).getyPosition(),);//todo : where's the keep?
        newGame.getSelectedUnits().clear();
        return "units disbanded successfully!";
    }

    public String buildEquipment(String equipmentName) {//todo : we must check many things here
        if (equipmentName.isEmpty()) {
            return "equipment name can't be empty";
        }
        for (Unit unit : newGame.getSelectedUnits()) {
            if (unit.getUnitType() != UnitType.ENGINEER) {
                return "this type of units can't build equipment!";
            }
        }
        EquipmentType equipmentType = EquipmentType.getEquipmentTypeByName(equipmentName);
        if (equipmentType == null) {
            return "equipment name is invalid!";
        }
        ArrayList<Unit> availableEngineers = new ArrayList<>();
        for (Unit unit : newGame.getSelectedUnits()) {
            if (!unit.isBusy()) availableEngineers.add(unit);
            if (availableEngineers.size() == equipmentType.getEngineerCount()) break;
        }
        if (availableEngineers.size() < equipmentType.getEngineerCount()) {
            return "you don't have enough engineers for build " + equipmentName;
        }
        if (equipmentType.getCost() > newGame.getSelectedUnits().get(0).getHomeland().getGold()) {
            return "you don't have enough gold";
        }
        for (Unit e : availableEngineers) e.setBusy(true);

//        Equipment equipment = new Equipment(equipmentType,newGame.getSelectedUnits().get(0).getxPosition(), newGame.getSelectedUnits().get(0).getyPosition(), newGame.getCurrentKingdom());
//        newGame.getCurrentKingdom().addEquipment(equipment);
        newGame.getCurrentKingdom().addGold(-equipmentType.getCost());
        Building siegeTent = new Building(BuildingType.SIEGE_TENT, newGame.getCurrentKingdom(), newGame.getSelectedUnits().get(0).getxPosition(), newGame.getSelectedUnits().get(0).getyPosition());
        newGame.getCurrentKingdom().addToSiegeBuildings(siegeTent);
        siegeTent.setEquipmentType(equipmentType);
        siegeTent.setDelay(equipmentType.getDelay());
        newGame.getCurrentMap().getMap()[ newGame.getSelectedUnits().get(0).getxPosition()][newGame.getSelectedUnits().get(0).getyPosition()].setBuilding(siegeTent);
        newGame.getCurrentMap().getMap()[ newGame.getSelectedUnits().get(0).getxPosition()][newGame.getSelectedUnits().get(0).getyPosition()].setHeight(siegeTent.getHeight());
        return "equipment " + equipmentName + "build successfully!";
    }

    public String digTunnel(String xCoordinate, String yCoordinate) {
        String output = checkNumber(xCoordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(yCoordinate);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(xCoordinate);
        int y = Integer.parseInt(yCoordinate);
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        return "";
    }

    public String pourOil(String direction) {
        return "";
    }

    public String airAttack(String xCoordinate, String yCoordinate) {
        String output = checkNumber(xCoordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(yCoordinate);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(xCoordinate);
        int y = Integer.parseInt(yCoordinate);
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (!(newGame.getSelectedUnits().get(0).getUnitType().getWeapon() == WeaponType.BOW) || !(newGame.getSelectedUnits().get(0).getUnitType().getWeapon() == WeaponType.CROSS_BOW)) {
            return "please enter a different command for non bowmen troops";
        }
        if (!newGame.isEnemyExistsInCell(x, y)) {
            return "there's no enemy here";
        }
        newGame.getAttackingUnits().clear();
        newGame.getAttackingUnits().addAll(newGame.getSelectedUnits());
        newGame.airAttack(x, y);
        return "fight is done successfully";
    }

    public String groundAttack(String xCoordinate, String yCoordinate) {
        String output = checkNumber(xCoordinate);
        if (!output.equals("")) {
            return output;
        }
        output = checkNumber(yCoordinate);
        if (!output.equals("")) {
            return output;
        }
        int x = Integer.parseInt(xCoordinate);
        int y = Integer.parseInt(yCoordinate);
        if (x < 0 || y < 0 || x >= newGame.getCurrentMap().getDimension() || y >= newGame.getCurrentMap().getDimension()) {
            return "your coordinates are not correct";
        }
        if (!isUnitSelected().equals("true")) {
            return isUnitSelected();
        }
        if (newGame.getSelectedUnits().get(0).getUnitType().getWeapon() == WeaponType.BOW || newGame.getSelectedUnits().get(0).getUnitType().getWeapon() == WeaponType.CROSS_BOW) {
            return "please enter a different command for bowmen";
        }
        if (!newGame.isEnemyExistsInCell(x, y)) {
            return "there's no enemy here";
        }
        newGame.getAttackingUnits().clear();
        newGame.getAttackingUnits().addAll(newGame.getSelectedUnits());
        return newGame.groundAttack(x, y);
    }

    public static String checkNumber(String X) {
        if (X.isEmpty()) {
            return "input can't be empty";
        }
        try {
            int x = Integer.parseInt(X);
        } catch (NumberFormatException e) {
            return "input must be a number";
        }
        return "";
    }

    private String isUnitSelected() {
        if (newGame.getSelectedUnits() == null || newGame.getSelectedUnits().size() == 0) {
            return "you have to select a unit first";
        }
        return "true";
    }

    public String stopPatrolling() {
        if (newGame.getPatrollingUnits() == null || newGame.getPatrollingUnits().size() == 0) {
            return "there's no unit patrolling";
        }
        newGame.getPatrollingUnits().clear();
        return "patrolling stopped";
    }

    public int getNumberOfRemainingPlayers() {//todo : this is needed to end the game if there's only one player left
        return UserDatabase.getPlayers().size();
    }

    public User getWinner() {//todo : this returns the winner of the game
        return null;
    }

    public void checkEquipment(Kingdom kingdom){
        for (int i = kingdom.getSiegeBuildings().size() - 1; i >= 0 ; i++) {
            Building tent = kingdom.getSiegeBuildings().get(i);
            if (tent.getDelay() == 0) {
                EquipmentType equipmentType = tent.getEquipmentType();
                Cell cell = newGame.getCurrentMap().getMap()[tent.getxPosition()][tent.getyPosition()];
                cell.setBuilding(null);
                int totalNumberOfEngineers = equipmentType.getEngineerCount();
                for (Unit unit : cell.getUnits()) {
                    if (totalNumberOfEngineers == 0) break;
                    if (unit.getUnitType() == UnitType.ENGINEER && unit.isBusy()) {
                        unit.setBusy(false);
                        totalNumberOfEngineers--;
                    }
                }
                Equipment equipment = new Equipment(equipmentType, tent.getxPosition(),tent.getyPosition(),kingdom);
                kingdom.removeFromSiegeBuildings(tent);
                cell.addUnits(equipment);
            } else tent.subtractDelay(1);
        }
    }
}
