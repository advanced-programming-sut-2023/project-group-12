package view;

import java.util.Scanner;

public class PauseMenu {
    public void run (Scanner scanner) {
        System.out.println("Welcome to pause menu!");
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                // TODO: handle going right back to main menu
            }
            else if (input.equalsIgnoreCase("resume")) {
                // TODO: handle going back to the game
            }
            // TODO: maybe handle saving the game!
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
