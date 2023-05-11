package view;

import Commands.StartMenuCommands;
import controller.StartMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StartMenu {
    public void run (Scanner scanner) {
        String input;
        Matcher chooseMap, addPlayer, removePlayer, removeAllPlayers, startGame, addRandomPlayers;
        StartMenuController controller = new StartMenuController();
        while (true) {
            input = scanner.nextLine();
            addPlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.ADD_PLAYER);
            removePlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_PLAYER);
            removeAllPlayers = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_ALL_PLAYERS);
            chooseMap = StartMenuCommands.getMatcher(input, StartMenuCommands.CHOOSE_MAP);
            startGame = StartMenuCommands.getMatcher(input, StartMenuCommands.START_GAME);
            addRandomPlayers = StartMenuCommands.getMatcher(input, StartMenuCommands.ADD_RANDOM_PLAYERS);
            if (input.equals("back")) {
                System.out.println("Welcome to main menu");
                return;
            }
            else if (addPlayer.find()) {
                if (addPlayer.group("username").isEmpty()) {
                    System.out.println("Username can't be empty");
                    continue;
                }
                System.out.println(controller.addPlayer(addPlayer.group("username")));
            }
            else if (addRandomPlayers.find()) {
                if (addRandomPlayers.group("number").isEmpty()) {
                    System.out.println("Number can't be empty");
                    continue;
                }
                System.out.println(controller.addRandomPlayers(addRandomPlayers.group("number")));
            }
            else if (removePlayer.find()) {
                if (removePlayer.group("username").isEmpty()) {
                    System.out.println("Username can't be empty");
                    continue;
                }
                System.out.println(controller.removePlayer(removePlayer.group("username")));
            }
            else if (removeAllPlayers.find()) {
                System.out.println(controller.removeAllPlayers());
            }
            else if (chooseMap.find()) {

            }
            else if (startGame.find()) {
                GameMenu menu = new GameMenu();
                menu.run(scanner);
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
