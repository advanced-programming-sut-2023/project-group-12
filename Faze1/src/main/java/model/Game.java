package model;

import model.map.Cell;
import model.people.Unit;
import model.map.Map;
import java.util.ArrayList;

public class Game {
    private Map currentMap;
    private Cell[][] map = currentMap.getMap();
    private ArrayList<User> players = UserDatabase.getPlayers();
    private User currentUser;
    private int roundsPassed;
    private ArrayList<Unit>selectedUnits;// todo : probably add a selected units class
    public void dropBuilding(int x, int y, String type) {
//        currentMap.getGameField().get(x).get(y).setBuilding();
    }

    public void selectBuilding(int x, int y) {

    }

    public void selectUnit(int x, int y) {

    }
    public void selectUnit(int x, int y, String type) {
        // we assume that x,y and type are not trouble makers
        for (Unit unit:map[x][y].getUnits()) {
            // check type
            selectedUnits.add(unit);
        }
    }
    public void moveUnit (int x, int y) {
        // int speed = selectedUnits.get(0).getSpeed;
        for (Unit unit : selectedUnits) {

        }
    }
    public void nextTurn() {

    }
}
