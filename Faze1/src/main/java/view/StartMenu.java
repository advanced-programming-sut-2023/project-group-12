package view;

import Commands.StartMenuCommands;
import controller.StartMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.*;
import java.util.Scanner;
import java.util.regex.Matcher;

public class StartMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        HBox setPlayers = new HBox();
        VBox setMap = new VBox();
        Pane pane = new Pane();
        RegisterMenu.setBackGround(pane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }
}
//    public void run(Scanner scanner) {
//        System.out.println("Welcome to start menu");
//        String input;
//        Matcher addPlayer, removePlayer, removeAllPlayers, startGame, addRandomPlayers;
//        StartMenuController controller = new StartMenuController();
//        while (true) {
//            input = scanner.nextLine();
//            addPlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.ADD_PLAYER);
//            removePlayer = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_PLAYER);
//            removeAllPlayers = StartMenuCommands.getMatcher(input, StartMenuCommands.REMOVE_ALL_PLAYERS);
//            startGame = StartMenuCommands.getMatcher(input, StartMenuCommands.START_GAME);
//            if (input.equalsIgnoreCase("back")) {
//                System.out.println("Welcome to main menu");
//                return;
//            } else if (addPlayer.find()) {
//                if (addPlayer.group("username").isEmpty()) {
//                    System.out.println("Username can't be empty");
//                    continue;
//                }
//                System.out.println(controller.addPlayer(addPlayer.group("username")));
//            } else if (removePlayer.find()) {
//                if (removePlayer.group("username").isEmpty()) {
//                    System.out.println("Username can't be empty");
//                    continue;
//                }
//                System.out.println(controller.removePlayer(removePlayer.group("username")));
//            } else if (removeAllPlayers.find()) {
//                System.out.println(controller.removeAllPlayers());
//            } else if (startGame.find()) {
//                String output = controller.canStartGame();
//                System.out.println(output);
//                if (output.equals("game started successfully")) {
//                    controller.playGame(scanner);
//                }
//            } else {
//                System.out.println("Invalid command!");
//            }
//        }
//    }
