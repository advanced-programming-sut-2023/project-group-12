package model;

import model.Property.WeaponType;
import model.map.Cell;
import model.people.Unit;
import model.map.Map;

import java.util.ArrayList;

import model.Building.Building;
import model.Building.BuildingType;
import model.people.UnitType;


public class Game {

    public Game(Map currentMap, ArrayList<Kingdom> players) {
        this.currentMap = currentMap;
        this.players = players;
        this.currentKingdom = players.get(0);
        this.roundsPassed = 0;
    }

    private Map currentMap;
    private Building selectedBuilding;
    private ArrayList<Kingdom> players;
    private Kingdom currentKingdom;
    private ArrayList<Unit> selectedUnits = new ArrayList<>();
    private ArrayList<Unit> patrollingUnits = new ArrayList<>();
    private ArrayList<Unit> attackingUnits = new ArrayList<>();

    public ArrayList<Unit> getAttackingUnits() {
        return attackingUnits;
    }

    private int patrollingUnitsNumberOfRounds = 0;

    public ArrayList<Unit> getPatrollingUnits() {
        return patrollingUnits;
    }

    public ArrayList<Unit> getSelectedUnits() {
        return selectedUnits;
    }

    public void setSelectedUnits(Unit unit) {
        this.selectedUnits.add(unit);
    }

    public void clearSelectedUnits() {
        this.selectedUnits.clear();
    }

    private int roundsPassed;

    public void dropBuilding(int x, int y, BuildingType buildingType) {
        Building building = BuildingType.getBuildingByBuildingType(buildingType, currentKingdom, x, y);
        currentMap.getMap()[x][y].setBuilding(building);
        currentMap.getMap()[x][y].setHeight(building.getHeight());
        if(building instanceof ProductionCenter ){
            currentKingdom.addProductionCenter((ProductionCenter) building);
        }else if (building instanceof UnitCreation) {
            currentKingdom.addToAllUnitCreations((UnitCreation) building);
        }else if (buildingType == BuildingType.STOCKPILE) {
            currentKingdom.addToStockPiles((Storage) building);
        } else if (buildingType == BuildingType.FOOD_STOCKPILE) {
            currentKingdom.addToFoodStockPiles((Storage) building);
        }else if (buildingType == BuildingType.ARMOURY){
            currentKingdom.addToWeapons((Storage) building);
            currentKingdom.addToDefensiveWeapon((Storage) building);
        }else if (buildingType == BuildingType.STABLE) {
            currentKingdom.addToStables((Storage) building);
        }
    }

    public void selectBuilding(int x, int y) {
        this.selectedBuilding = currentMap.getMap()[x][y].getBuilding();
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public Kingdom getCurrentKingdom() {
        return currentKingdom;
    }

    public boolean isEnemyExistsInCell(int x, int y) {
        for (Unit unit : currentMap.getMap()[x][y].getUnits()) {
            if (unit.getHomeland() != currentKingdom) {
                return true;
            }
        }
        return false;
    }

    public void nextTurn() {
        //TODO: add unit method for each turn
        int currentKingdomNumber = -1;
        for(int i = 0; i < players.size(); i++) {
            if(currentKingdom == players.get(i)){
                currentKingdomNumber = i;
            }
        }
        if(currentKingdomNumber == (players.size() - 1)){
            roundsPassed++;
            currentKingdom = players.get(0);
            for(Kingdom kingdom : players){
                for(ProductionCenter productionCenter : kingdom.getAllProductionCenters()){
                    productionCenter.run();
                }
                for (UnitCreation unitCreation: kingdom.getAllUnitCreations()) {
                    unitCreation.run();
                }
            }
        }
        else{
            currentKingdom = players.get(currentKingdomNumber+1);
        }
    }

    //todo: this at the beginning of each turn
    public String patrolUnit(int xStart, int yStart, int xEnd, int yEnd) {
        if (finalPath(xStart, yStart, xEnd, yEnd) == null) {
            return "no path found between the two points";
        }
        if (finalPath(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xStart, yStart) == null) {
            return "units can't get there";
        }
        int speed = this.patrollingUnits.get(0).getSpeed();
        if (patrollingUnitsNumberOfRounds % 2 == 0) {//go towards the start
            ArrayList<Cell> path = finalPath(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xStart, yStart);
            if (speed < path.size()) {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xStart, yStart, this.patrollingUnits);
            } else {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xStart, yStart, this.patrollingUnits);
                patrollingUnitsNumberOfRounds++;
                speed = speed - path.size() + 1;
                moveUnitWithSpeed(xStart, yStart, xEnd, yEnd, speed, this.patrollingUnits);
            }
        } else if (patrollingUnitsNumberOfRounds % 2 == 1) {//go towards the end
            ArrayList<Cell> path = finalPath(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xEnd, yEnd);
            if (speed < path.size()) {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xEnd, yEnd, this.patrollingUnits);
            } else {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xEnd, yEnd, this.patrollingUnits);
                patrollingUnitsNumberOfRounds++;
                speed = speed - path.size() + 1;
                moveUnitWithSpeed(xEnd, yEnd, xStart, yStart, speed, this.patrollingUnits);
            }
        }
        return "";
    }

    private void moveUnitWithSpeed(int xStart, int yStart, int xEnd, int yEnd, int speed, ArrayList<Unit> units) {
        //todo: handle the method for special cases
        ArrayList<Cell> path = finalPath(xStart, yStart, xEnd, yEnd);
        for (int i = 0; i < speed && i < path.size() - 2; i++) {
            for (int j = units.size() - 1; j >= 0; j--) {
                path.get(i).getUnits().remove(units.get(j));
                path.get(i + 1).getUnits().add(units.get(j));
            }
        }
        //todo: complete the method
    }

    //todo: check the move method
    public String moveUnit(int xStart, int yStart, int xEnd, int yEnd, ArrayList<Unit> units) {
        //todo: handle the method for special cases
        ArrayList<Cell> path = finalPath(xStart, yStart, xEnd, yEnd);
        if (path == null) {
            return "no path found for these units";
        }
        int speed = units.get(0).getSpeed();
        for (int i = 0; i < speed && i < path.size() - 2; i++) {
            for (int j = units.size() - 1; j >= 0; j--) {
                path.get(i).getUnits().remove(units.get(j));
                path.get(i + 1).getUnits().add(units.get(j));
            }
        }
        return "units moved successfully";
        //todo: complete the method
    }

    private ArrayList<Cell> finalPath(int xStart, int yStart, int xEnd, int yEnd) {
        runPath(xStart, yStart, xEnd, yEnd);
        ArrayList<Cell> path = new ArrayList<>();
        Cell cell = currentMap.getMap()[xEnd][yEnd];
        path.add(cell);
        if (cell.getFather() == null) {
            return null;
        }
        while (!path.contains(currentMap.getMap()[xStart][yStart])) {
            cell = cell.getFather();
            path.add(cell);
        }
        ArrayList<Cell> correctOrder = new ArrayList<>();
        for (int i = path.size() - 1; i >= 0; i--) {
            correctOrder.add(path.get(i));
        }
        for (int i = 0; i < currentMap.getDimension(); i++) {
            for (int j = 0; j < currentMap.getDimension(); j++) {
                currentMap.getMap()[i][j].setFather(null);
            }
        }
        return correctOrder;
    }

    private void runPath(int xStart, int yStart, int xEnd, int yEnd) {
        ArrayList<Cell> way = new ArrayList<>();
        way.add(currentMap.getMap()[xStart][yStart]);
        currentMap.getMap()[xStart][yStart].setInThePath(true);
        int previousSize = 0;
        int currentSize = 1;
        while (!way.contains(currentMap.getMap()[xEnd][yEnd])) {
            if (previousSize == currentSize) {
                break;
            }
            for (int i = 0; i < currentMap.getDimension(); i++) {
                for (int j = 0; j < currentMap.getDimension(); j++) {
                    if (!currentMap.getMap()[i][j].isInThePath() && currentMap.getMap()[i][j].isPassable()) {
                        for (Cell cell : neighbors(i, j)) {
                            if (cell.isInThePath()) {
                                way.add(currentMap.getMap()[i][j]);
                                currentMap.getMap()[i][j].setInThePath(true);
                                currentMap.getMap()[i][j].setFather(cell);
                            }
                        }
                    }
                    if (currentMap.getMap()[xEnd][yEnd].isInThePath()) {
                        return;
                    }
                }
            }
            previousSize = currentSize;
            currentSize = way.size();
        }
        for (int i = 0; i < currentMap.getDimension(); i++) {
            for (int j = 0; j < currentMap.getDimension(); j++) {
                currentMap.getMap()[i][j].setInThePath(false);
            }
        }
    }

    private ArrayList<Cell> neighbors(int x, int y) {
        ArrayList<Cell> output = new ArrayList<>();
        if (currentMap.getMap()[x - 1][y] != null) {
            output.add(currentMap.getMap()[x - 1][y]);
        }
        if (currentMap.getMap()[x + 1][y] != null) {
            output.add(currentMap.getMap()[x + 1][y]);
        }
        if (currentMap.getMap()[x][y - 1] != null) {
            output.add(currentMap.getMap()[x][y - 1]);
        }
        if (currentMap.getMap()[x][y + 1] != null) {
            output.add(currentMap.getMap()[x][y + 1]);
        }
        return output;
    }

    public Building getSelectedBuilding() {
        return selectedBuilding;
    }

    public void setSelectedBuilding(Building selectedBuilding) {
        this.selectedBuilding = selectedBuilding;
    }

    public ArrayList<Kingdom> getPlayers() {
        return players;
    }

    public String groundAttack(int x, int y) {
        ArrayList<Cell> path = finalPath(attackingUnits.get(0).getxPosition(), attackingUnits.get(0).getyPosition(), x, y);
        if (path == null ) {
            return "enemy can't be reached";
        }
        if (path.size() - 1 > attackingUnits.get(0).getSpeed()) {
            return "enemy out of range, please move your units closer";
        }
        moveUnit(attackingUnits.get(0).getxPosition(), attackingUnits.get(0).getyPosition(), x, y, attackingUnits);
        fight(x, y);
        return "fight is done";
    }

    private void fight(int x, int y) {
        Cell cell = this.getCurrentMap().getMap()[x][y];
        for (int j = 0; j < attackingUnits.size(); j++) {
            Unit unit = attackingUnits.get(j);
            for (int i = 0; i < cell.getUnits().size(); i++) {
                Unit unit1 = cell.getUnits().get(i);
                if (unit1.getHomeland() != unit.getHomeland()) {
                    oneIsDead:
                    while (true) {
                        if (unit.getHitPoint() > 0) {
                            if (unit1.getHitPoint() > 0) {
                                if (!unit1.isBeingHit()) {
                                    unitOnUnitFight(unit, unit1);
                                    unit1.setBeingHit(true);
                                    if (!unit1.isHittingSomeOne() && !unit.isBeingHit()) {
                                        unitOnUnitFight(unit1, unit);
                                        unit.setBeingHit(true);
                                    }
                                }
                                if (unit1.isBeingHit() && i == cell.getUnits().size() - 1) {
                                    unitOnUnitFight(unit, unit1);
                                    unit1.setBeingHit(true);
                                    if (!unit1.isHittingSomeOne() && !unit.isBeingHit()) {
                                        unitOnUnitFight(unit1, unit);
                                        unit.setBeingHit(true);
                                    }
                                }
                            } else {
                                break oneIsDead;
                            }
                        } else {
                            break oneIsDead;
                        }
                    }
                }
            }
        }
        for (int i = attackingUnits.size() - 1; i >= 0; i--) {
            if (attackingUnits.get(i).getHitPoint() <= 0)
                attackingUnits.remove(i);
        }
        for (int i = cell.getUnits().size() - 1; i >= 0; i--) {
            if (cell.getUnits().get(i).getHitPoint() <= 0)
                cell.getUnits().remove(i);
        }
        for (Unit unit : attackingUnits) {
            unit.setBeingHit(false);
            unit.setHittingSomeOne(false);
        }
        for (Unit unit : cell.getUnits()) {
            unit.setBeingHit(false);
            unit.setHittingSomeOne(false);
        }
    }

    private void unitOnUnitFight(Unit attacker, Unit mast) {
        mast.decreaseHitPoint(attacker.getUnitType().getAttackPower());
    }

    public void airAttack(int x, int y) {// is it okay todo: ask mohammad
        if (Math.pow(x - attackingUnits.get(0).getxPosition(), 2) + Math.pow(y - attackingUnits.get(0).getyPosition(), 2) <= attackingUnits.get(0).getUnitType().getRange()) {
            fight(x, y);
        }
    }

}
