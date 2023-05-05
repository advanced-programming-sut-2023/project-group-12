package view;

import Commands.StartMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class StartMenu {
    public void run (Scanner scanner) {
        String input;
        Matcher chooseMap, addPlayer, removePlayer, removeAllPlayers, startGame, addRandomPlayers;
        while (true) {
            input = scanner.nextLine();
            addPlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.ADD_PLAYER);
            removePlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_PLAYER);
            removeAllPlayers = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_ALL_PLAYERS);
            chooseMap = StartMenuCommands.getMatcher(input, StartMenuCommands.CHOOSE_MAP);
            startGame = StartMenuCommands.getMatcher(input, StartMenuCommands.START_GAME);
            addRandomPlayers = StartMenuCommands.getMatcher(input, StartMenuCommands.ADD_RANDOM_PLAYERS);
            if (input.equals("back")) {
                return;
            }
            else if (addPlayer.find()) {

            }
            else if (addRandomPlayers.find()) {

            }
            else if (removePlayer.find()) {

            }
            else if (removeAllPlayers.find()) {

            }
            else if (chooseMap.find()) {

            }
            else if (startGame.find()) {

            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
