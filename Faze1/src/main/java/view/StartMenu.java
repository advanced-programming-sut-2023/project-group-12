package view;

import Commands.StartMenuCommands;
import controller.StartMenuController;
import model.Game;
import model.Kingdom;
import model.UserDatabase;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class StartMenu {
    public void run (Scanner scanner) {
        System.out.println("Welcome to start menu");
        String input;
        Matcher addPlayer, removePlayer, removeAllPlayers, startGame, addRandomPlayers;
        StartMenuController controller = new StartMenuController();
        while (true) {
            input = scanner.nextLine();
            addPlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.ADD_PLAYER);
            removePlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_PLAYER);
            removeAllPlayers = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_ALL_PLAYERS);
            startGame = StartMenuCommands.getMatcher(input, StartMenuCommands.START_GAME);
            if (input.equalsIgnoreCase("back")) {
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
            else if (startGame.find()) {
                String output = controller.canStartGame();
                System.out.println(output);
                if (output.equals("game started successfully")) {
                    controller.playGame(scanner);
                }
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
