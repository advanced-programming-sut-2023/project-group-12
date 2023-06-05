package view;

import controller.ProfileController;
import controller.RegisterMenuController;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import model.UserDatabase;

import java.awt.*;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class ProfileMenu extends Application {//todo: scoreboard, show pass, style
    private static Label usernameErrorLabel = new Label();
    private static Label emailErrorLabel = new Label();
    private static Label nicknameErrorLabel = new Label();
    private static Label sloganErrorLabel = new Label();
    private static Label passwordErrorLabel = new Label("Password is too short");
    private static TextField fieldToChange = new TextField();
    private static ProfileController controller = null;

    @Override
    public void start(Stage stage) throws Exception {
        // JUST FOR AVOIDING ERROR
        UserDatabase.loadUsers();
        UserDatabase.setCurrentUser(UserDatabase.getUsers().get(UserDatabase.getUsers().size() - 1));

        controller = new ProfileController(UserDatabase.getCurrentUser());

        Pane pane = new Pane();
        Circle circle = avatarPlace(pane,stage);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();

        VBox vBox = getvBox(width, height, pane);

        pane.getChildren().add(vBox);

        pane.getChildren().add(circle);
        circle.toFront();
        initializePosition(width, height);
        getBack(stage,vBox);
        pane.setPrefSize(width, height);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static void getBack(Stage stage,VBox vBox) {
        Button back = new Button("back");
        back.setOnAction(actionEvent -> {
            try {
                new MainMenu().start(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        vBox.getChildren().add(back);
    }

    private static void initializePosition(double width, double height) {
        usernameErrorLabel.setLayoutX(width / 2 + 50);
        usernameErrorLabel.setLayoutY(height / 2 - 100);
        usernameErrorLabel.setTextFill(javafx.scene.paint.Color.RED);

        emailErrorLabel.setLayoutX(width / 2 + 50);
        emailErrorLabel.setLayoutY(height / 2 - 100);
        emailErrorLabel.setTextFill(javafx.scene.paint.Color.RED);

        nicknameErrorLabel.setLayoutX(width / 2 + 50);
        nicknameErrorLabel.setLayoutY(height / 2 - 100);
        nicknameErrorLabel.setTextFill(javafx.scene.paint.Color.RED);

        sloganErrorLabel.setLayoutX(width / 2 + 50);
        sloganErrorLabel.setLayoutY(height / 2 - 100);
        sloganErrorLabel.setTextFill(javafx.scene.paint.Color.RED);

        passwordErrorLabel.setTextFill(javafx.scene.paint.Color.RED);

        fieldToChange.setLayoutX(width / 2 - 100);
        fieldToChange.setLayoutY(height / 2 - 100);
    }

    private static VBox getvBox(double width, double height, Pane pane) {
        VBox vBox = new VBox();
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);
        vBox.setLayoutX(width / 2 - 100);
        vBox.setLayoutY(height / 2 - 100);
        Button changeUsername = getChangeUsername(width, height, pane, vBox);
        Button changeNickname = getChangeNickname(width, height, pane, vBox);
        Button changeEmail = getChangeEmail(width, height, pane, vBox);
        Button changePassword = getChangePassword(width, height, pane, vBox);
        Button changeSlogan = getChangeSlogan(width, height, pane, vBox);
        vBox.getChildren().addAll(changeUsername, changeNickname, changeEmail, changePassword, changeSlogan);
        return vBox;
    }

    private static Button getChangeSlogan(double width, double height, Pane pane, VBox vBox) {
        Button changeSlogan = new Button("Change Slogan");
        changeSlogan.setOnMouseEntered(mouseEvent -> {
            changeSlogan.setStyle("-fx-background-color: #ff0000; ");
            if (UserDatabase.getCurrentUser().getSlogan().equals("")) {
                changeSlogan.setText("slogan is empty");
            }
            else {
                changeSlogan.setText("UserDatabase.getCurrentUser().getSlogan()");
            }
        });
        changeSlogan.setOnMouseExited(mouseEvent -> {
            changeSlogan.setStyle("");
            changeSlogan.setText("Change Slogan");
        });
        changeSlogan.setPrefSize(200, 50);
        changeSlogan.setOnAction(actionEvent -> {
            fieldToChange.setPromptText("slogan");
            fieldToChange.setText(UserDatabase.getCurrentUser().getSlogan());
            fieldToChange.textProperty().addListener((observableValue, s, t1) -> {
                String output = controller.changeSlogan(t1);
                if (output.equals("true")) {
                    sloganErrorLabel.setText("");
                } else {
                    sloganErrorLabel.setText(output);
                    sloganErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
                }
            });
            Button back = new Button("back");
            Button submit = new Button("submit");
            Button removeSlogan = new Button("remove slogan");
            removeSlogan.setLayoutX(width / 2);
            removeSlogan.setLayoutY(height / 2 - 50);
            removeSlogan.setOnAction(actionEvent1 -> {
                if (UserDatabase.getCurrentUser().getSlogan().equals("")) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("you don't have any slogan");
                    alert.show();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setContentText("are you sure you want to remove your slogan?");
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        controller.removeSlogan();
                        Alert alert1 = new Alert(Alert.AlertType.INFORMATION);
                        alert1.setContentText("slogan removed successfully");
                        alert1.showAndWait();
                        pane.getChildren().removeAll(fieldToChange, submit, back, sloganErrorLabel, removeSlogan);
                        pane.getChildren().add(vBox);
                    }
                }
            });
            submit.setLayoutX(width / 2 - 100);
            submit.setLayoutY(height / 2 - 50);
            back.setLayoutX(0);
            back.setLayoutY(0);
            submit.setOnAction(actionEvent1 -> {
                String output = controller.changeSlogan(fieldToChange.getText());
                if (output.equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("slogan changed successfully");
                    alert.showAndWait();
                    pane.getChildren().removeAll(fieldToChange, submit, back, sloganErrorLabel, removeSlogan);
                    pane.getChildren().add(vBox);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(output);
                    alert.show();
                }
            });
            back.setOnAction(actionEvent1 -> {
                pane.getChildren().removeAll(fieldToChange, back, sloganErrorLabel, submit, removeSlogan);
                pane.getChildren().add(vBox);
            });
            pane.getChildren().remove(vBox);
            pane.getChildren().addAll(fieldToChange, sloganErrorLabel, back, submit, removeSlogan);
        });
        return changeSlogan;
    }

    private static Button getChangePassword(double width, double height, Pane pane, VBox vBox) {
        Button changePassword = new Button("Change Password");
        changePassword.setOnMouseEntered(mouseEvent -> changePassword.setStyle("-fx-background-color: #ff0000;"));
        changePassword.setOnMouseExited(mouseEvent -> changePassword.setStyle(""));
        changePassword.setPrefSize(200, 50);
        Dialog<Void> dialog = new Dialog<>();
        dialog.setTitle("Change Password");
        dialog.setHeaderText("Enter your old password and new password");
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        GridPane gridPane = new GridPane();
        PasswordField oldPassword = new PasswordField();
        oldPassword.setPromptText("old password");
        PasswordField newPassword = new PasswordField();
        newPassword.setPromptText("new password");
        gridPane.add(passwordErrorLabel, 1, 1);
        newPassword.textProperty().addListener((observableValue, s, t1) -> {
            if (!RegisterMenuController.isPasswordWeak(t1).equals("true")) {
                passwordErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
                passwordErrorLabel.setText(RegisterMenuController.isPasswordWeak(t1));
            } else {
                passwordErrorLabel.setText("");
            }
        });
        gridPane.add(oldPassword, 0, 0);
        gridPane.add(newPassword, 0, 1);
        dialog.getDialogPane().setContent(gridPane);
        changePassword.setOnAction(actionEvent -> {
            Optional<Void> result = dialog.showAndWait();
            if (result.isPresent()) {
                String output = null;
                try {
                    output = controller.changePassword(oldPassword.getText(), newPassword.getText());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
                if (output.equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("password changed successfully");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(output);
                    alert.show();
                }
            }
        });
        return changePassword;
    }

    private static Button getChangeEmail(double width, double height, Pane pane, VBox vBox) {
        Button changeEmail = new Button("Change Email");
        changeEmail.setOnMouseEntered(mouseEvent -> {
            changeEmail.setStyle("-fx-background-color: #ff0000;");
            changeEmail.setText(UserDatabase.getCurrentUser().getEmail());
        });
        changeEmail.setOnMouseExited(mouseEvent -> {
            changeEmail.setStyle("");
            changeEmail.setText("Change Email");
        });
        changeEmail.setPrefSize(200, 50);
        changeEmail.setOnAction(actionEvent -> {
            fieldToChange.setPromptText("email");
            fieldToChange.setText(UserDatabase.getCurrentUser().getEmail());
            fieldToChange.textProperty().addListener((observableValue, s, t1) -> {
                String output = controller.changeEmail(t1);
                if (output.equals("true")) {
                    emailErrorLabel.setText("");
                } else {
                    emailErrorLabel.setText(output);
                    emailErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
                }
            });
            Button submit = new Button("submit");
            submit.setLayoutX(width / 2 - 100);
            submit.setLayoutY(height / 2 - 50);
            Button back = new Button("back");
            submit.setOnAction(actionEvent1 -> {
                String output = controller.changeEmail(fieldToChange.getText());
                if (output.equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("email changed successfully");
                    alert.showAndWait();
                    pane.getChildren().removeAll(fieldToChange, submit, back, emailErrorLabel);
                    pane.getChildren().add(vBox);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(output);
                    alert.show();
                }
            });
            back.setOnAction(actionEvent1 -> {
                pane.getChildren().removeAll(fieldToChange, back, emailErrorLabel, submit);
                pane.getChildren().add(vBox);
            });
            pane.getChildren().remove(vBox);
            pane.getChildren().addAll(fieldToChange, emailErrorLabel, back, submit);
        });
        return changeEmail;
    }

    private static Button getChangeUsername(double width, double height, Pane pane, VBox vBox) {
        Button changeUsername = new Button("Change Username");
        changeUsername.setOnMouseEntered(mouseEvent -> {
            changeUsername.setStyle("-fx-background-color: #ff0000;");
            changeUsername.setText(UserDatabase.getCurrentUser().getUsername());
        });
        changeUsername.setOnMouseExited(mouseEvent -> {
            changeUsername.setStyle("");
            changeUsername.setText("Change Username");
        });
        changeUsername.setPrefSize(200, 50);
        changeUsername.setOnAction(actionEvent -> {
            fieldToChange.setPromptText("username");
            fieldToChange.setText(UserDatabase.getCurrentUser().getUsername());
            fieldToChange.textProperty().addListener((observableValue, s, t1) -> {
                String output = controller.changeUsername(t1);
                if (!output.equals("true")) {
                    usernameErrorLabel.setText(output);
                    usernameErrorLabel.setTextFill(javafx.scene.paint.Color.RED);
                } else if (t1.length() > 0) {
                    usernameErrorLabel.setText("");
                }
            });
            Button submit = new Button("submit");
            submit.setLayoutX(width / 2 - 100);
            submit.setLayoutY(height / 2 - 50);
            Button back = new Button("back");
            submit.setOnAction(actionEvent1 -> {
                String output = controller.changeUsername(fieldToChange.getText());
                if (output.equals("true")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("username changed successfully");
                    alert.showAndWait();
                    pane.getChildren().removeAll(fieldToChange, submit, back, usernameErrorLabel);
                    pane.getChildren().add(vBox);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText(output);
                    alert.show();
                }
            });
            back.setOnAction(actionEvent1 -> {
                pane.getChildren().removeAll(fieldToChange, back, usernameErrorLabel, submit);
                pane.getChildren().add(vBox);
            });
            pane.getChildren().remove(vBox);
            pane.getChildren().addAll(fieldToChange, usernameErrorLabel, back, submit);
        });
        return changeUsername;
    }

    private static Button getChangeNickname(double width, double height, Pane pane, VBox vBox) {
        Button changeNickname = new Button("Change Nickname");
        changeNickname.setOnMouseEntered(mouseEvent -> {
            changeNickname.setStyle("-fx-background-color: #ff0000;");
            changeNickname.setText(UserDatabase.getCurrentUser().getNickname());
        });
        changeNickname.setOnMouseExited(mouseEvent -> {
            changeNickname.setStyle("");
            changeNickname.setText("Change Nickname");
        });
        changeNickname.setPrefSize(200, 50);
        changeNickname.setOnAction(actionEvent -> {
            fieldToChange.setPromptText("nickname");
            fieldToChange.setText(UserDatabase.getCurrentUser().getNickname());
            fieldToChange.textProperty().addListener((observableValue, s, t1) -> {
                if (t1.length() > 0) {
                    nicknameErrorLabel.setText("");
                }

            });
            Button submit = new Button("submit");
            submit.setLayoutX(width / 2 - 100);
            submit.setLayoutY(height / 2 - 50);
            Button back = new Button("back");
            submit.setOnAction(actionEvent1 -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                String output = controller.changeNickname(fieldToChange.getText());
                alert.setContentText(output);
                alert.showAndWait();
                pane.getChildren().removeAll(fieldToChange, submit, back, nicknameErrorLabel);
                pane.getChildren().add(vBox);
            });
            back.setOnAction(actionEvent1 -> {
                pane.getChildren().removeAll(fieldToChange, back, nicknameErrorLabel, submit);
                pane.getChildren().add(vBox);
            });
            pane.getChildren().remove(vBox);
            pane.getChildren().addAll(fieldToChange, nicknameErrorLabel, back, submit);
        });
        return changeNickname;
    }

    private static Circle avatarPlace(Pane pane,Stage stage) {
        Circle circle = new Circle();
        circle.setRadius(50);
        Image image = new Image(UserDatabase.getCurrentUser().getAvatar());
        circle.setFill(new ImagePattern(image));
        circle.setCenterX(60);
        circle.setCenterY(60);
        circle.setOnMouseClicked(mouseEvent -> {
            try {
                new AvatarMenu().start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        return circle;
    }
}
//    private ProfileController controller;
//
//    public void run(Scanner scanner) throws NoSuchAlgorithmException {
//        System.out.println("Welcome to profile menu!");
//        controller = new ProfileController(UserDatabase.getCurrentUser());
//        String input;
//        Matcher matcher;
//        while (true) {
//            input = scanner.nextLine();
//            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_EMAIL)).find())
//                changeEmail(matcher);
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_USERNAME)).find())
//                changeUsername(matcher);
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_NICKNAME)).find())
//                changeNickname(matcher);
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_SLOGAN)).find())
//                changeSlogan(matcher);
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_PASSWORD)).find())
//                changePassword(matcher);
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_REMOVE_SLOGAN)).find())
//                removeSlogan();
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_HIGH_SCORE)).find())
//                displayHighScore();
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_RANK)).find())
//                displayRank();
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_SLOGAN)).find())
//                displaySlogan();
//            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY)).find())
//                displayAll();
//            else if (input.equalsIgnoreCase("back"))
//                return;
//            else
//                System.out.println("Please try again!");
//        }
//    }
//
//
//    private void changeUsername(Matcher matcher) {
//        System.out.println(controller.changeUsername(matcher.group("username")));
//    }
//
//    private void changeNickname(Matcher matcher) {
//        System.out.println(controller.changeNickname(matcher.group("nickname")));
//    }
//
//    private void changePassword(Matcher matcher) throws NoSuchAlgorithmException {
//        System.out.println(controller.changePassword(matcher.group("oldPassword"), matcher.group("newPassword")));
//    }
//
//    private void changeEmail(Matcher matcher) {
//        System.out.println(controller.changeEmail(matcher.group("email")));
//    }
//
//    private void changeSlogan(Matcher matcher) {
//        System.out.println(controller.changeSlogan(matcher.group("slogan")));
//    }
//
//    private void removeSlogan() {
//        System.out.println(controller.removeSlogan());
//    }
//
//    private void displayHighScore() {
//        System.out.println(controller.displayHighScore());
//    }
//
//    private void displayRank() {
//        System.out.println(controller.displayRank());
//    }
//
//    private void displaySlogan() {
//        System.out.println(controller.displaySlogan());
//    }
//
//    private void displayAll() {
//        System.out.println(controller.displayAll());
//    }
