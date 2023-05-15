package view;

import java.util.Scanner;

public class PauseMenu {
    public String run(Scanner scanner) {
        System.out.println("Welcome to pause menu!");
        String input;
        while (true) {
            input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Are you sure you want to exit? (yes/anything else)");
                input = scanner.nextLine();
                if (input.equalsIgnoreCase("yes")) {
                    return "exit";
                } else {
                    System.out.println("exit canceled");
                }
            } else if (input.equalsIgnoreCase("resume")) {
                return "resume";
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
