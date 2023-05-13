package model;

import model.map.Cell;
import model.people.Unit;
import model.map.Map;

import java.util.ArrayList;

import model.Building.Building;
import model.Building.BuildingType;
import model.people.UnitType;


public class Game {


    private Map currentMap;
    private Building selectedBuilding;
    private ArrayList<Kingdom> players;

    {
        for (User user: UserDatabase.getPlayers()){
            players.add(new Kingdom(user));
        }
    }
    private Kingdom currentKingdom;
    //todo: next turn & current kingdom
    private ArrayList<Unit> selectedUnits = new ArrayList<>();
    private ArrayList<Unit> patrollingUnits = new ArrayList<>();
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
            ArrayList<Cell>path = finalPath(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xStart, yStart);
            if (speed < path.size()) {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xStart, yStart);
            }
            else {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xStart, yStart);
                patrollingUnitsNumberOfRounds++;
                speed = speed - path.size()+1;
                moveUnitWithSpeed(xStart, yStart, xEnd, yEnd,speed);
            }
        }
        else if (patrollingUnitsNumberOfRounds % 2 == 1) {//go towards the end
            ArrayList<Cell>path = finalPath(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xEnd, yEnd);
            if (speed < path.size()) {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xEnd, yEnd);
            }
            else {
                moveUnit(this.patrollingUnits.get(0).getxPosition(), this.patrollingUnits.get(0).getyPosition(), xEnd, yEnd);
                patrollingUnitsNumberOfRounds++;
                speed = speed - path.size()+1;
                moveUnitWithSpeed(xEnd, yEnd, xStart, yStart,speed);
            }
        }
        return "";
    }
    private void moveUnitWithSpeed(int xStart, int yStart, int xEnd, int yEnd, int speed) {
        //todo: handle the method for special cases
        ArrayList<Cell> path = finalPath(xStart, yStart, xEnd, yEnd);
        for (int i = 0; i < speed && i < path.size() - 2; i++) {
            for (int j = selectedUnits.size() - 1; j >= 0; j--) {
                path.get(i).getUnits().remove(selectedUnits.get(j));
                path.get(i + 1).getUnits().add(selectedUnits.get(j));
            }
        }
        //todo: complete the method
    }
    //todo: check the move method
    public String moveUnit(int xStart, int yStart, int xEnd, int yEnd) {
        //todo: handle the method for special cases
        ArrayList<Cell> path = finalPath(xStart, yStart, xEnd, yEnd);
        if (path == null) {
            return "no path found for these units";
        }
        int speed = this.getSelectedUnits().get(0).getSpeed();
        for (int i = 0; i < speed && i < path.size() - 2; i++) {
            for (int j = selectedUnits.size() - 1; j >= 0; j--) {
                path.get(i).getUnits().remove(selectedUnits.get(j));
                path.get(i + 1).getUnits().add(selectedUnits.get(j));
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
}
