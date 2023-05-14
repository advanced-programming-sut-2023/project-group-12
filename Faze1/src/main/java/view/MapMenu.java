package view;

import Commands.MapMenuCommands;
import controller.mapmenu.MapMenuController;
import model.map.Map;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MapMenu {
    private Map map;
    public MapMenu(int size, int kingdomNumber) {
        map = new Map(size, kingdomNumber);
    }

    public MapMenu(Map map) {
        this.map = map;
    }

    public void run (Scanner scanner) {
        System.out.println("Welcome to map menu!");
        String input;
        Matcher setCellTexture, setCellsTexture, clear, dropRock, dropTree, showMap, mapUp, showDetail, selectMap;
        MapMenuController controller = new MapMenuController(map);
        while (true) {
            input = scanner.nextLine();
            setCellTexture = MapMenuCommands.getMatcher(input, MapMenuCommands.SET_TEXTURE_OF_CELL);
            setCellsTexture = MapMenuCommands.getMatcher(input, MapMenuCommands.SET_TEXTURE_OF_CELLS);
            clear = MapMenuCommands.getMatcher(input, MapMenuCommands.CLEAR_CELL);
            dropRock = MapMenuCommands.getMatcher(input, MapMenuCommands.DROP_ROCK);
            dropTree = MapMenuCommands.getMatcher(input, MapMenuCommands.DROP_TREE);
            showMap = MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_MAP);
            mapUp = MapMenuCommands.getMatcher(input, MapMenuCommands.MAP_UP);
            showDetail = MapMenuCommands.getMatcher(input, MapMenuCommands.SHOW_DETAILS);
            selectMap = MapMenuCommands.getMatcher(input, MapMenuCommands.SELECT_MAP);
            if (setCellTexture.find()) {
                int x = Integer.parseInt(setCellTexture.group("xCoordinate"));
                int y = Integer.parseInt(setCellTexture.group("yCoordinate"));
                String type = setCellTexture.group("type");
                System.out.println(controller.setCellTexture(x,y,type));
            }
            else if (setCellsTexture.find()) {
                int x1 = Integer.parseInt(setCellsTexture.group("x1Coordinate"));
                int y1 = Integer.parseInt(setCellsTexture.group("y1Coordinate"));
                int x2 = Integer.parseInt(setCellsTexture.group("x2Coordinate"));
                int y2 = Integer.parseInt(setCellsTexture.group("y2Coordinate"));
                String type = setCellsTexture.group("type");
                System.out.println(controller.setCellsTexture(x1,y1,x2,y2,type));
            }
            else if (clear.find()) {
                System.out.println(controller.clear(Integer.parseInt(clear.group("xCoordinate")), Integer.parseInt(clear.group("yCoordinate"))));
            }
            else if (dropRock.find()) {
                System.out.println(controller.dropRock(Integer.parseInt(dropRock.group("xCoordinate")), Integer.parseInt(dropRock.group("yCoordinate")), dropRock.group("direction").toCharArray()[0]));
            }
            else if (dropTree.find()) {
                System.out.println(controller.dropTree(Integer.parseInt(dropTree.group("xCoordinate")), Integer.parseInt(dropTree.group("yCoordinate")), dropTree.group("type")));
            } else if (showMap.find()) {
                System.out.println(controller.showMap(Integer.parseInt(showMap.group("x")), Integer.parseInt(showMap.group("y"))));
            } else if (mapUp.find()) {
                System.out.println(controller.mapUp(mapUp.group("direction"), Integer.parseInt(mapUp.group("number"))));
            } else if (showDetail.find()) {
                System.out.print(controller.showDetail(Integer.parseInt(showDetail.group("x")), Integer.parseInt(showDetail.group("y"))));
            } else if (selectMap.find()) {
                System.out.println(controller.selectMap());
            } else if (input.equalsIgnoreCase("back")) {
                return;
            }
            else {
                System.out.println("Invalid command!");
            }
         }
    }
}
