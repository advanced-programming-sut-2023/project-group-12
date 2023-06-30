package view;

import Commands.MainMenuCommands;
import controller.MainMenuController;
import javafx.application.Application;
import javafx.geometry.Pos;
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

public class MainMenu extends Application {
    private static Stage stage;
    @Override
    public void start(Stage stage) throws Exception {
        MainMenu.stage = stage;
        Pane pane = new Pane();
        RegisterMenu.setBackGround(pane);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        VBox vBox = new VBox();
        vBox.setLayoutX(width / 2 - 100);
        vBox.setLayoutY(height / 2 - 100);
        Button goToProfileMenu = getProfileMenu();
        HBox mapMenuHBox = new HBox();
        mapMenuHBox.setSpacing(10);
        javafx.scene.control.TextField dimensionTextField = new javafx.scene.control.TextField();
        javafx.scene.control.TextField numberOfKingdoms = new TextField();
        Button goToMapMenu = new Button("Go to map menu");
        mapMenuHBox.getChildren().addAll(goToMapMenu, dimensionTextField, numberOfKingdoms);
        handleMapMenu(dimensionTextField, numberOfKingdoms, goToMapMenu, stage);
        Button goToStartMenu = getStartMenu();
        Button userLogout = getLogout(stage);
        Button exit = getExit();
        vBox.getChildren().addAll(goToStartMenu, mapMenuHBox, goToProfileMenu, userLogout, exit);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(30);
        pane.getChildren().add(vBox);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void handleMapMenu(TextField dimensionTextField, TextField numberOfKingdoms, Button goToMapMenu, Stage stage) {
        dimensionTextField.setPromptText("dimension");
        numberOfKingdoms.setPromptText("kingdoms");
        goToMapMenu.setOnMouseClicked(mouseEvent -> {
            try {
                String dimension = dimensionTextField.getText();
                String kingdomNumber = numberOfKingdoms.getText();
                (new MainMenuController()).goToMapMenu(Integer.parseInt(dimension), Integer.parseInt(kingdomNumber), stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private static Button getExit() {
        Button exit = new Button("Exit");
        exit.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setContentText("Are you sure you want to exit?");
            alert.showAndWait();
            if (alert.getResult().getText().equals("OK"))
                System.exit(0);
        });
        exit.setAlignment(Pos.CENTER);
        exit.setPrefSize(200, 30);
        return exit;
    }

    
    private static Button getLogout(Stage stage) {
        Button userLogout = new Button("User logout");
        userLogout.setOnMouseClicked(event -> {
            EnterMenu enterMenu = new EnterMenu();
            try {
                UserDatabase.setCurrentUser(null);
                enterMenu.start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        userLogout.setAlignment(Pos.CENTER);
        userLogout.setPrefSize(200, 30);
        return userLogout;
    }

    
    private static Button getStartMenu() {
        Button goToStartMenu = new Button("Go to start menu");
        goToStartMenu.setOnMouseClicked(event -> {
            StartMenu startMenu = new StartMenu();
            try {
                startMenu.start(MainMenu.stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        goToStartMenu.setAlignment(Pos.CENTER);
        goToStartMenu.setPrefSize(200, 30);
        return goToStartMenu;
    }

    
    private static Button getProfileMenu() {
        Button goToProfileMenu = new Button("Go to profile menu");
        goToProfileMenu.setOnMouseClicked(event -> {
            ProfileMenu profileMenu = new ProfileMenu();
            try {
                profileMenu.start(EnterMenu.getStage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        goToProfileMenu.setAlignment(Pos.CENTER);
        goToProfileMenu.setPrefSize(200, 30);
        return goToProfileMenu;
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
