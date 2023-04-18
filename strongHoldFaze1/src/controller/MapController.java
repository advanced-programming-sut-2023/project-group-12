package controller;

import model.Map;

public class MapController {
    private Map map;
    // this gets the map created in createMapMenu
    public void setTexture (int x, int y, String texture) {
        map.getGameField().get(x).get(y).setTexture(texture);
    }
    public void dropSth (int x, int y, String name) {

    }

    public void newMap(int dimension) {

    }

    public void showMap() {

    }

    public void showDetail(int x, int y) {

    }
    public void clear(int x, int y) {

    }
     public void dropRock(int x, int y, String direction) {

     }

     public void dropTree(int x, int y, String type) {

     }

     public void dropBuilding(int x, int y, String type) {

     }

     public void dropUnit(int x, int y, String type, int count) {

     }

}
