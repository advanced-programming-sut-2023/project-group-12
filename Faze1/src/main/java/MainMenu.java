import Commands.MainMenuCommands;

import java.util.regex.Matcher;

public class MainMenu {
    // private User currentUser;
    public void run () {
        String input;
        Matcher mapMenu,profileMenu,startMenu,userLogout;
        while (true) {
            input = Main.scanner.nextLine();
            mapMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_MAP_MENU);
            profileMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_PROFILE_MENU);
            startMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_START_MENU);
            userLogout = MainMenuCommands.getMatcher(input, MainMenuCommands.USER_LOGOUT);
            if (mapMenu.find()) {
                // MapMenu menu = new MapMenu();
                // menu.run();
            }
            else if (profileMenu.find()) {
                // ProfileMenu menu = new ProfileMenu();
                //menu .run();
            }
            else if (startMenu.find()) {
                //StartMenu menu = new StartMenu();
                //menu.run();
            }
            else if (userLogout.find()) { // for now, it goes back to log in menu, but it should go to the menu that has access to both menus
                System.out.println("user logged out successfully!");
                return;
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
