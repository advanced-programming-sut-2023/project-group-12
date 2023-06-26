package view;

import controller.StartMenuController;
import javafx.application.Application;
import javafx.event.Event;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.UserDatabase;

import java.awt.*;

public class StartMenu extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        UserDatabase.loadUsers();
        UserDatabase.setCurrentUser(UserDatabase.getUserByUsername("matin"));
        StartMenuController controller = new StartMenuController();
        HBox setPlayers = new HBox();
        VBox setMap = new VBox();
        Pane pane = new Pane();
        RegisterMenu.setBackGround(pane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        Button back = getBack(stage);
        TextField addPlayer = new TextField();
        Button addPlayerButton = getAddPlayerButton(controller, setPlayers, width, addPlayer);
        Button removePlayer = new Button("removePlayer");
        removePlayer.setOnMouseClicked(event -> {
            String output = controller.removePlayer(addPlayer.getText());
            Alert alert;
            if (output.equals("Player removed successfully")) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(output);
                addPlayer.setText("");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(output);
            }
            alert.show();
        });
        removePlayer.setLayoutX(width / 2 - 100);
        removePlayer.setLayoutY(100);
        Button removeAllPlayers = new Button("removeAllPlayers");
        removeAllPlayers.setOnMouseClicked(event -> {
            Alert alert;
            String output = controller.removeAllPlayers();
            if (output.equals("All Players removed successfully")) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(output);
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(output);
            }
            alert.show();
        });
        Button startGame = new Button("startGame");
        startGame.setOnMouseClicked(Event::consume);//todo);
        setPlayers.getChildren().addAll(addPlayer, addPlayerButton, removePlayer, removeAllPlayers);
        pane.getChildren().addAll(back, setPlayers, setMap);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static Button getAddPlayerButton(StartMenuController controller, HBox setPlayers, double width, TextField addPlayer) {
        Button addPlayerButton = new Button("Add Player");
        addPlayer.setPromptText("Username");
        addPlayerButton.setOnMouseClicked(event -> {
            Alert alert;
            if (addPlayer.getText().isEmpty()) {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username can't be empty");
                alert.show();
            }
            String output = controller.addPlayer(addPlayer.getText());
            if (output.equals("Player successfully added")) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(output);
                addPlayer.setText("");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText(output);
            }
            alert.show();
        });
        setPlayers.setLayoutX(width / 2 - 100);
        setPlayers.setLayoutY(50);
        return addPlayerButton;
    }

    private static Button getBack(Stage stage) {
        Button back = new Button("Back");
        back.setOnMouseClicked(event -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        back.setLayoutX(0);
        back.setLayoutY(0);
        return back;
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
