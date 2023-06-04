package view;

import controller.RegisterMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.User;
import model.UserDatabase;

import java.awt.*;

public class ProfileMenu extends Application {//todo : check dialog instead of label
    private static Label usernameErrorLabel = new Label("Username can't be empty");
    private static Label emailErrorLabel = new Label("Email can't be empty");
    private static Label nicknameErrorLabel = new Label("Nickname can't be empty");
    private static Label sloganErrorLabel = new Label("this is already your slogan");

    @Override
    public void start(Stage stage) throws Exception {
        // JUST FOR AVOIDING ERROR
//        User user = new User("admin", "admin", "admin", "admin", "admin");
//        UserDatabase.setCurrentUser(user);
        VBox vBox = fieldsPlace();
        Pane pane = new Pane(vBox);
        Circle circle = avatarPlace(pane);
        pane.getChildren().add(circle);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private static VBox fieldsPlace() {//todo : placing and styling and password
        VBox vBox = new VBox();
        vBox.setLayoutX(150);
        vBox.setLayoutY(100);
        vBox.setSpacing(20);
        HBox username = getUsername(vBox);
        HBox email = getEmail(vBox);
        HBox nickname = getNickname(vBox);
        HBox slogan = getSlogan(vBox);
        vBox.getChildren().addAll(username, email, nickname, slogan);
        return vBox;
    }

    private static HBox getSlogan(VBox vBox) {
        HBox slogan = new HBox();
        Text sloganText = new Text("Slogan : ");
        Text sloganField = new Text(UserDatabase.getCurrentUser().getSlogan());
        TextField textField = new TextField();
        CheckBox change = new CheckBox();
        Button submit = new Button("Submit");
        change.setOnMouseClicked(actionEvent -> {
            if (change.isSelected()) {
                slogan.getChildren().remove(sloganField);
                slogan.getChildren().addAll(textField, submit);
                if (vBox.getChildren().contains(sloganErrorLabel)) {
                    vBox.getChildren().remove(sloganErrorLabel);
                }
                vBox.getChildren().add(sloganErrorLabel);
                sloganErrorLabel.setVisible(false);
            } else if (!sloganErrorLabel.isVisible()) {

                slogan.getChildren().removeAll(textField, submit);
                slogan.getChildren().add(sloganField);
                vBox.getChildren().remove(sloganErrorLabel);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("this is already your slogan");
                alert.show();
                change.setSelected(true);
            }
        });
        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.equals(UserDatabase.getCurrentUser().getSlogan())) {
                sloganErrorLabel.setVisible(true);
            } else {
                sloganErrorLabel.setVisible(false);
            }
        });
        submit.setOnAction(actionEvent -> {
            if (sloganErrorLabel.isVisible()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("this is already your slogan");
                alert.show();
                return;
            } else {
                if (textField.getText().isEmpty()) {
                    textField.setText("slogan is empty");
                }
                UserDatabase.getCurrentUser().setSlogan(textField.getText());
                sloganField.setText(textField.getText());

                slogan.getChildren().removeAll(textField, submit);
                slogan.getChildren().add(sloganField);
                change.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("slogan changed successfully");
                alert.showAndWait();
            }
        });
        slogan.getChildren().addAll(change, sloganText, sloganField);
        return slogan;
    }

    private static HBox getNickname(VBox vBox) {
        HBox nickname = new HBox();
        Text nicknameText = new Text("Nickname : ");
        Text nicknameField = new Text(UserDatabase.getCurrentUser().getNickname());
        TextField textField = new TextField();
        CheckBox change = new CheckBox();
        Button submit = new Button("Submit");
        change.setOnMouseClicked(actionEvent -> {
            if (change.isSelected()) {
                nickname.getChildren().remove(nicknameField);
                nickname.getChildren().addAll(textField, submit);
                vBox.getChildren().add(nicknameErrorLabel);
            } else if (!nicknameErrorLabel.isVisible()) {
                vBox.getChildren().remove(nicknameErrorLabel);

                nickname.getChildren().removeAll(textField, submit);
                nickname.getChildren().add(nicknameField);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nickname can't be empty");
                alert.show();
                change.setSelected(true);
            }
        });
        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.isEmpty()) {
                nicknameErrorLabel.setVisible(true);
            } else {
                nicknameErrorLabel.setVisible(false);
            }
        });
        submit.setOnMouseClicked(actionEvent -> {
            if (textField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nickname can't be empty");
                alert.show();
            } else {
                UserDatabase.getCurrentUser().setNickname(textField.getText());
                nicknameField.setText(textField.getText());
                vBox.getChildren().remove(nicknameErrorLabel);

                nickname.getChildren().removeAll(textField, submit);
                nickname.getChildren().add(nicknameField);
                change.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Nickname changed successfully");
                alert.showAndWait();
            }
        });
        nickname.getChildren().addAll(change, nicknameText, nicknameField);
        return nickname;
    }

    private static HBox getEmail(VBox vBox) {
        HBox email = new HBox();
        Text emailText = new Text("Email : ");
        Text emailField = new Text(UserDatabase.getCurrentUser().getEmail());
        TextField textField = new TextField();
        CheckBox change = new CheckBox();
        Button submit = new Button("Submit");
        change.setOnMouseClicked(actionEvent -> {
            if (change.isSelected()) {
                email.getChildren().remove(emailField);
                email.getChildren().addAll(textField, submit);
                vBox.getChildren().add(emailErrorLabel);
            } else if (!emailErrorLabel.isVisible()) {
                vBox.getChildren().remove(emailErrorLabel);

                email.getChildren().removeAll(textField, submit);
                email.getChildren().add(emailField);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Email is not correct");
                alert.showAndWait();
                change.setSelected(true);
            }
        });
        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.isEmpty()) {
                emailErrorLabel.setVisible(true);
            } else if (UserDatabase.isEmailExists(t1.toUpperCase())) {
                emailErrorLabel.setText("Email already exists");
            } else if (!RegisterMenuController.isEmailFormatCorrect(t1)) {
                emailErrorLabel.setText("Email is not correct");
            } else {
                emailErrorLabel.setVisible(false);
            }
        });
        submit.setOnMouseClicked(actionEvent -> {
            if (emailErrorLabel.isVisible()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Email is not correct");
                alert.showAndWait();
            } else {
                UserDatabase.getCurrentUser().setEmail(textField.getText());
                emailField.setText(textField.getText());

                email.getChildren().removeAll(textField, submit);
                vBox.getChildren().remove(emailErrorLabel);
                email.getChildren().add(emailField);
                change.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Email changed successfully");
                alert.showAndWait();
            }
        });
        email.getChildren().addAll(change, emailText, emailField);
        return email;
    }

    private static HBox getUsername(VBox vBox) {
        HBox username = new HBox();
        Text usernameText = new Text("Username : ");
        Text usernameField = new Text(UserDatabase.getCurrentUser().getUsername());
        Button submit = new Button("Submit");
        TextField textField = new TextField();
        textField.setPromptText("Enter new username");
        CheckBox change = new CheckBox();
        change.setOnMouseClicked(actionEvent -> {//todo : placing and styling
            if (change.isSelected()) {
                username.getChildren().remove(usernameField);
                username.getChildren().addAll(textField, submit);
                vBox.getChildren().add(usernameErrorLabel);
            } else if (!usernameErrorLabel.isVisible()) {
                vBox.getChildren().remove(usernameErrorLabel);
                username.getChildren().removeAll(textField, submit);
                username.getChildren().add(usernameField);
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username is not correct");
                alert.showAndWait();
                change.setSelected(true);
            }
        });
        textField.textProperty().addListener((observableValue, s, t1) -> {
            if (t1.isEmpty())
                usernameErrorLabel.setVisible(true);
            else if (RegisterMenuController.isUsernameUsed(t1)) {
                usernameErrorLabel.setText("Username already exists");
            } else if (!RegisterMenuController.isCorrectUsername(t1)) {
                usernameErrorLabel.setText("Username format is not correct");
            } else {
                usernameErrorLabel.setVisible(false);
            }
        });
        submit.setOnMouseClicked(actionEvent -> {
            if (usernameErrorLabel.isVisible()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Username is not correct");
                alert.showAndWait();
            } else {
                UserDatabase.getCurrentUser().setUsername(textField.getText());
                usernameField.setText(textField.getText());
                username.getChildren().removeAll(textField, submit);
                vBox.getChildren().remove(usernameErrorLabel);
                username.getChildren().add(usernameField);
                change.setSelected(false);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Username changed successfully");
                alert.showAndWait();
            }
        });
        username.getChildren().addAll(change, usernameText, usernameField);
        return username;
    }

    private static Circle avatarPlace(Pane pane) {
        Circle circle = new Circle();
        circle.setRadius(50);
        Image image = new Image(UserDatabase.getCurrentUser().getAvatar());
        circle.setFill(new ImagePattern(image));
        circle.setCenterX(60);
        circle.setCenterY(60);
        circle.setOnMouseClicked(mouseEvent -> {
            try {
                new AvatarMenu().start(EnterMenu.getStage());
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
