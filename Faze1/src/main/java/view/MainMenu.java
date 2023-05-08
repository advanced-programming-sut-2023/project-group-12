package view;

import Commands.MainMenuCommands;

import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu {
    // private User currentUser;
    public void run(Scanner scanner) {
        System.out.println("Welcome to main menu");
        String input;
        Matcher mapMenu, profileMenu, startMenu, userLogout;
        while (true) {
            input = scanner.nextLine();
            mapMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_MAP_MENU);
            profileMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_PROFILE_MENU);
            startMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_START_MENU);
            userLogout = MainMenuCommands.getMatcher(input, MainMenuCommands.USER_LOGOUT);
            if (mapMenu.find()) {
                if (mapMenu.group("size").isEmpty()) {
                    System.out.println("Size can't be empty");
                }
                if (Integer.parseInt(mapMenu.group("size"))!= 200 || Integer.parseInt(mapMenu.group("size"))!= 400 ) {
                    System.out.println("Size must be 200 or 400");
                }
                MapMenu menu = new MapMenu(Integer.parseInt(mapMenu.group("size")));
                menu.run(scanner);
            } else if (profileMenu.find()) {
                 view.ProfileMenu menu = new view.ProfileMenu();
                menu .run(scanner);
            } else if (startMenu.find()) {
                StartMenu menu = new StartMenu();
                menu.run(scanner);
            } else if (userLogout.find()) {
                System.out.println("user logged out successfully!");
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
