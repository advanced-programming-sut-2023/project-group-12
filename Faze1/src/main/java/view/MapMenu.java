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


    public void run (Scanner scanner) {
        String input;
        Matcher setCellTexture, setCellsTexture, clear, dropRock, dropTree;
        MapMenuController controller = new MapMenuController(map);
        while (true) {
            input = scanner.nextLine();
            setCellTexture = MapMenuCommands.getMatcher(input, MapMenuCommands.SET_TEXTURE_OF_CELL);
            setCellsTexture = MapMenuCommands.getMatcher(input, MapMenuCommands.SET_TEXTURE_OF_CELLS);
            clear = MapMenuCommands.getMatcher(input, MapMenuCommands.CLEAR_CELL);
            dropRock = MapMenuCommands.getMatcher(input, MapMenuCommands.DROP_ROCK);
            dropTree = MapMenuCommands.getMatcher(input, MapMenuCommands.DROP_TREE);
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
                System.out.println(controller.dropRock(Integer.parseInt(dropTree.group("xCoordinate")), Integer.parseInt(dropTree.group("yCoordinate")), dropTree.group("direction").toCharArray()[0]));
            }
            else if (dropTree.find()) {
                System.out.println(controller.dropTree(Integer.parseInt(dropTree.group("xCoordinate")), Integer.parseInt(dropTree.group("yCoordinate")), dropTree.group("type")));
            }
            else if (input.equals("back")) {
                return;
            }
            else {
                System.out.println("Invalid command!");
            }
         }
    }
}
