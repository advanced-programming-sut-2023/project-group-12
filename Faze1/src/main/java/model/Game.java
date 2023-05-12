package model;

import model.map.Cell;
import model.people.Unit;
import model.map.Map;

import java.util.ArrayList;

import model.Building.Building;
import model.Building.BuildingType;


public class Game {


    private Map currentMap;
    private Building selectedBuilding;
    private ArrayList<User> players;
    private Kingdom currentKingdom;

    private int roundsPassed;

    public void dropBuilding(int x, int y, BuildingType buildingType) {
        Building building = BuildingType.getBuildingByBuildingType(buildingType, currentKingdom, x, y);
        currentMap.getMap()[x][y].setBuilding(building);
        currentMap.getMap()[x][y].setHeight(building.getHeight());
    }

    public void selectBuilding(int x, int y) {
        //todo: go to building menu controller
    }




    public Map getCurrentMap() {
        return  currentMap;
    }

    public Kingdom getCurrentKingdom() {
        return currentKingdom;
    }

    public boolean isEnemyExistsInCell(int x, int y) {
        for(Unit unit : currentMap.getMap()[x][y].getUnits()){
            if(unit.getHomeland() != currentKingdom) {
                return true;
            }
        }
        return false;
    }
    public void nextTurn() {

    }
    public void moveUnit(int xStart, int yStart, int xEnd, int yEnd) {
        //todo: handle the method for special cases
        if (!currentMap.getMap()[xEnd][yEnd].isPassable()) {
            // it can't be reached
        }
        ArrayList<Cell> path = finalPath(xStart, yStart, xEnd, yEnd);
        //todo: complete the method
    }
    private ArrayList<Cell> finalPath (int xStart, int yStart, int xEnd, int yEnd) {
        ArrayList<Cell> path = new ArrayList<>();
        Cell cell = currentMap.getMap()[xEnd][yEnd];
        path.add(cell);
        while (!path.contains(currentMap.getMap()[xStart][yStart])) {
            cell = cell.getFather();
            path.add(cell);
        }
        ArrayList<Cell> correctOrder = new ArrayList<>();
        for (int i = path.size()-1; i >= 0; i--) {
            correctOrder.add(path.get(i));
        }
        return correctOrder;
    }
    private ArrayList<Cell> path(int xStart, int yStart, int xEnd, int yEnd) {
        ArrayList<Cell> way = new ArrayList<>();
        way.add(currentMap.getMap()[xStart][yStart]);
        currentMap.getMap()[xStart][yStart].setInThePath(true);
        while (!way.contains(currentMap.getMap()[xEnd][yEnd])) {
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
                        return way;
                    }
                }
            }
        }
        for (int i = 0; i < currentMap.getDimension(); i++) {
            for (int j = 0; j < currentMap.getDimension(); j++) {
                currentMap.getMap()[i][j].setInThePath(false);
            }
        }
        return way;
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
}
