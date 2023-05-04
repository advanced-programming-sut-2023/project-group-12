package controller.mapmenu;

import model.map.Map;
import model.map.Type;
import model.people.Unit;

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

    public void showMap(int x, int y) {
        System.out.println();
        for (int i = y + 5; i >= y - 5 && i >= 0; i--) {
            if (i >= map.getMap().length)
                continue;
            for (int j = x - 5; j <= x + 5 && j <= map.getMap().length; j++) {
                if (j <= 0)
                    continue;
                showCell(j, i);
            }
            System.out.println();
        }
    }

    private void showCell(int x, int y) {
        map.setLastX(x);
        map.setLastY(y);
        String building = ".";
        String soldier = ".";
        String tree = ".";
        if (map.getMap()[x][y].getBuilding() != null)
            building = "B";
        if (!map.getMap()[x][y].getUnits().isEmpty())
            soldier = "S";
        if (map.getMap()[x][y].getTree() != null)
            tree = "T";
        int backgroundTheme = 0;
        for (Type type : Type.values()) {
            if (map.getMap()[x][y].getType().name().equals(type.name())) {
                System.out.print(backgroundTheme);
                backgroundTheme %= 8;
                backgroundTheme += 40;
                break;
            }
            backgroundTheme++;
        }
        System.out.print(backgroundTheme + map.getMap()[x][y].getType().name());
        System.out.print("\033[" + backgroundTheme + "m" + building + soldier + tree + "|" + "\033[0m");
    }

    private void mapUp(String direction, int value) {
        if (direction.equals("n")) {
            showMap(map.getLastX(), map.getLastY() + value);
        } else if (direction.equals("e")) {
            showMap(map.getLastX() + value, map.getLastY());
        } else if (direction.equals("w")) {
            showMap(map.getLastX() - value, map.getLastY());
        } else if (direction.equals("s")) {
           showMap(map.getLastX(), map.getLastY() - value);
        }
    }

    private String showDetail(int x, int y) {
        //todo : getBuilding.toString and unit.toString is its name
        if (isCorrectCoordinate(x, y)) {
            System.out.println("Building : " + map.getMap()[x - 1][y - 1].getBuilding().toString());
            System.out.println("Tree : " + map.getMap()[x - 1][y - 1].getTree().name());
            System.out.println("Texture : " + map.getMap()[x - 1][y - 1].getType().name());
            System.out.println("Soldiers : ");
            for (Unit unit : map.getMap()[x - 1][y - 1].getUnits()) {
                System.out.println(unit.toString());
            }
        } return "your coordinate is incorrect";
    }
}
