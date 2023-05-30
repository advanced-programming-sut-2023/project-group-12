package view;

import Commands.MainMenuCommands;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class MainMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        RegisterMenu.setBackGround(pane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        Button goToMapMenu = new Button("Go to map menu");
        Button goToProfileMenu = new Button("Go to profile menu");
        Button goToStartMenu = new Button("Go to start menu");
        Button userLogout = new Button("User logout");
        Button exit = new Button("Exit");
        pane.getChildren().addAll(goToMapMenu, goToProfileMenu, goToStartMenu, userLogout, exit);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
//    public void run(Scanner scanner) throws NoSuchAlgorithmException {
//        System.out.println("Welcome to main menu");
//        String input;
//        Matcher mapMenu, profileMenu, startMenu, userLogout;
//        MainMenuController controller = new MainMenuController();
//        while (true) {
//            input = scanner.nextLine();
//            mapMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_MAP_MENU);
//            profileMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_PROFILE_MENU);
//            startMenu = MainMenuCommands.getMatcher(input, MainMenuCommands.GO_TO_START_MENU);
//            userLogout = MainMenuCommands.getMatcher(input, MainMenuCommands.USER_LOGOUT);
//            if (mapMenu.find()) {
//                if (mapMenu.group("size").isEmpty()) {
//                    System.out.println("Size can't be empty");
//                }
//                if (mapMenu.group("kingdomNumber").isEmpty()) {
//                    System.out.println("Kingdom number can't be empty");
//                }
//                int size;
//                int kingdomNumber;
//                try {
//                    size = Integer.parseInt(mapMenu.group("size"));
//                    kingdomNumber = Integer.parseInt(mapMenu.group("kingdomNumber"));
//                } catch (NumberFormatException e) {
//                    System.out.println("You must input numbers");
//                    continue;
//                }
//                if (!(size == 200 || size == 400)) {
//                    System.out.println("Size must be 200 or 400");
//                    continue;
//                }
//                if (kingdomNumber > 8 || kingdomNumber < 2) {
//                    System.out.println("kingdom number out of bounds");
//                    continue;
//                }
//                MapMenu menu = new MapMenu(size, kingdomNumber);
//                menu.run(scanner);
//                System.out.println("Welcome back to main menu");
//            } else if (profileMenu.find()) {
//                view.ProfileMenu menu = new view.ProfileMenu();
//                menu.run(scanner);
//                System.out.println("Welcome back to main menu");
//            } else if (startMenu.find()) {
//                if (!controller.isMapSelected()) {
//                    System.out.println("Go back to map menu and choose your map");
//                    continue;
//                }
//                StartMenu menu = new StartMenu();
//                menu.run(scanner);
//                System.out.println("Welcome back to main menu");
//            } else if (userLogout.find()) {
//                controller.removeUserLoggedIn();
//                System.out.println("user logged out successfully!");
//                EnterMenu menu = new EnterMenu();
////                menu.run(scanner);
//            } else {
//                System.out.println("Invalid command!");
//            }
//        }
//    }
