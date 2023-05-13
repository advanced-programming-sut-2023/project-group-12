package controller.mapmenu;

import model.map.Map;
import model.map.TextureType;
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
                    map.getMap()[x - 1][y - 1].setTextureType(getFloorTypeByName(type).getType());
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
                if (!isBuildingExist(x1, x2, y1, y2)) {
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
            map.getMap()[x - 1][y - 1].setTextureType(TextureType.EARTH);
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
                return "rock drop successfully";
            }
            return "there exist building";
        } else
            return "your coordinate is incorrect";
    }

    public String dropTree(int x, int y, String type) {
        if (isCorrectCoordinate(x, y)) {
            if (getTreeTypeByName(type) != null) {
                if (map.getMap()[x - 1][y - 1].getBuilding() == null) {
                    if (!map.getMap()[x - 1][y - 1].getTextureType().isWatery()) {
                        map.getMap()[x - 1][y - 1].setTree(getTreeTypeByName(type).getTree());
                        return "tree dropped successfully";
                    } else
                        return "this cell is watery";
                } else
                    return "there exist building";
            } else
                return "haven\'t this tree";
        } else
            return "your coordinate is incorrect";
    }

    public String showMap(int x, int y) {
        map.setLastX(x - 1);
        map.setLastY(y - 1);
        String output = "\n";
        for (int i = y + 4; i >= y - 6 && i >= 0; i--) {
            if (i >= map.getMap().length)
                continue;
            for (int j = x - 6; j <= x + 4 && j <= map.getMap().length; j++) {
                if (j < 0)
                    continue;
                output += showCell(j, i);
            }
            output += "\n";
        }
        return output;
    }

    private String showCell(int x, int y) {
        String output = "";
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
        for (TextureType type : TextureType.values()) {
            if (map.getMap()[x][y].getTextureType().name().equals(type.name())) {
                backgroundTheme %= 8;
                backgroundTheme += 40;
                break;
            }
            backgroundTheme++;
        }
        output += "\033[" + backgroundTheme + "m" + building + soldier + tree + "|" + "\033[0m";
        return output;
    }

    public String mapUp(String direction, int value) {
        if (direction.equals("n")) {
            if (map.getLastY() + value <= map.getDimension())
                return showMap(map.getLastX() + 1, map.getLastY() + value + 1);
            else
                return "out of bounds";
        } else if (direction.equals("e")) {
            if (map.getLastX() + value <= map.getDimension())
                return showMap(map.getLastX() + value + 1, map.getLastY() + 1);
            else
                return "out of bounds";
        } else if (direction.equals("w")) {
            if (map.getLastY() - value < 0)
                return showMap(map.getLastX() - value + 1, map.getLastY() + 1);
            else
                return "out of bounds";
        } else if (direction.equals("s")) {
            if (map.getLastY() - value <= map.getDimension())
                return showMap(map.getLastX() + 1, map.getLastY() - value + 1);
            else
                return "out of bounds";
        }
        return "Wrong direction";
    }

    public String showDetail(int x, int y) {
        String output = "";
        if (isCorrectCoordinate(x, y)) {
            output += "Building : ";
            if (map.getMap()[x - 1][y - 1].getBuilding() != null)
                output += map.getMap()[x - 1][y - 1].getBuilding().getBuildingType().name().toLowerCase() + "\n";
            else
                output += "NONE" + "\n";
            output += "Tree : ";
            if (map.getMap()[x - 1][y - 1].getTree() != null)
                output += map.getMap()[x - 1][y - 1].getTree().name() + "\n";
            else
                output += "NONE" + "\n";
            output += "Texture : " + map.getMap()[x - 1][y - 1].getTextureType().name() + "\n";
            output += "Soldiers : ";
            if (!map.getMap()[x - 1][y - 1].getUnits().isEmpty()) {
                output += "\n";
                for (Unit unit : map.getMap()[x - 1][y - 1].getUnits()) {
                    output += unit.getUnitType().name().toLowerCase() + " owner = " + unit.getHomeland().getOwner().getUsername() + "\n";
                }
            } else
                output += "NONE" + "\n";
        } else
            output += "Your coordinate is incorrect";
        return output;
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
            for (int j = y2; j <= y1; j++) {
                if (map.getMap()[i - 1][j - 1].getBuilding() != null)
                    return true;
            }
        }
        return false;
    }

    private void setMapFloor(int x1, int y1, int x2, int y2, TextureType type) {
        for (int i = x1; i <= x2; i++)
            for (int j = y2; j <= y1; j++)
                map.getMap()[i - 1][j - 1].setTextureType(type);
    }
}
