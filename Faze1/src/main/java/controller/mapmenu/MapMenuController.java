package controller.mapmenu;

import model.map.Map;
import model.map.Type;

public class MapMenuController {

    private Map map;

    public MapMenuController(Map map) {
        this.map = map;
    }

    public String setCellTexture(int x, int y, String type) {
        if (isCorrectCoordinate(x, y)) {
            if (getFloorTypeByName(type) != null) {
                if (map.getMap()[x - 1][y - 1].getBuilding() == null) {
                    map.getMap()[x - 1][y - 1].setType(getFloorTypeByName(type).getType());
                    return "texture changed successfully";
                } else
                    return "there exists building";
            } else
                return "type <" + type + "> is incorrect";
        } else
            return "your coordinate is out of bound";
    }

    public String setCellsTexture(int x1, int y1, int x2, int y2, String type) {
        if (isCorrectCoordinate(x1, y1) && isCorrectCoordinate(x2, y2) &&
                x2 > x1 && y1 > y2) {
            if (getFloorTypeByName(type) != null) {
                if (isBuildingExist(x1, x2, y1, y2)) {
                    setMapFloor(x1, y1, x2, y2, getFloorTypeByName(type).getType());
                    return "texture changed successfully";
                } else
                    return "there exist building";
            } else
                return "type <" + type + "> is incorrect";
        } else
            return "your coordinate is incorrect";
    }

    public String clear(int x, int y) {
        if (isCorrectCoordinate(x, y)) {
            map.getMap()[x - 1][y - 1].setBuilding(null);
            map.getMap()[x - 1][y - 1].setType(Type.EARTH);
            map.getMap()[x - 1][y - 1].setTree(null);
            map.getMap()[x - 1][y - 1].setUnits(null);
            return "cell cleared successfully";
        } else
            return "your coordinate is incorrect";
    }

    public String dropRock(int x, int y, char direction) {
        if (isCorrectCoordinate(x, y)) {
            if (map.getMap()[x - 1][y - 1].getBuilding() == null) {
                if (Character.toString(direction).equals("n")) {
                    setCellTexture(x, y, "rockN");
                } else if (Character.toString(direction).equals("e")) {
                    setCellTexture(x, y, "rockE");
                } else if (Character.toString(direction).equals("w")) {
                    setCellTexture(x, y, "rockW");
                } else if (Character.toString(direction).equals("s")) {
                    setCellTexture(x, y, "rockS");
                }
            }
            return "there exist building";
        } else
            return "your coordinate is incorrect";
    }

    public String dropTree(int x, int y, String type) {
        if (isCorrectCoordinate(x, y)) {
            if (getTreeTypeByName(type) != null) {
                if (map.getMap()[x - 1][y - 1].getBuilding() == null) {
                    map.getMap()[x - 1][y - 1].setTree(getTreeTypeByName(type).getTree());
                    return "tree dropped successfully";
                } else
                    return "there exist building";
            } else
                return "haven\'t this tree";
        } else
            return "your coordinate is incorrect";
    }

    private boolean isCorrectCoordinate(int x, int y) {
        if (x > map.getDimension() || x < 1
                || y > map.getDimension() || y < 1)
            return false;
        return true;
    }

    private FloorType getFloorTypeByName(String input) {
        for (FloorType floorType : FloorType.values()) {
            if (FloorType.hasMatcher(input, floorType))
                return floorType;
        }
        return null;
    }

    private TreeType getTreeTypeByName(String input) {
        for (TreeType treeType : TreeType.values())
            if (TreeType.hasMatcher(input, treeType))
                return treeType;
        return null;
    }

    private boolean isBuildingExist(int x1, int x2, int y1, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y2; y2 <= y1; j++) {
                if (map.getMap()[i - 1][j - 1].getBuilding() != null)
                    return true;
            }
        }
        return false;
    }

    private void setMapFloor(int x1, int y1, int x2, int y2, Type type) {
        for (int i = x1; i <= x2; i++)
            for (int j = y2; j <= y1; j++)
                map.getMap()[i - 1][j - 1].setType(type);
    }
}
