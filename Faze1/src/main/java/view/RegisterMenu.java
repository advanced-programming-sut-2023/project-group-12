package view;

import Enums.Errors;
import controller.RegisterMenuController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.awt.*;

public class RegisterMenu extends Application {
    Label usernameErrorLabel = new Label();
    CheckBox checkBox = new CheckBox("Show Password");
    Button randomPassword = new Button("Random Password");
    @Override
    public void start(Stage stage) throws Exception {
        Pane pane = new Pane();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        pane.setPrefSize(width, height);
        TextField username = usernameField(width, height);
        PasswordField password = passwordField(width, height);
        TextField email = emailField(width, height);
        TextField nickname = nicknameField(width, height);
        pane.getChildren().addAll(username, usernameErrorLabel,password,checkBox,email,nickname);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
    }

    private void usernameValidate(String newValue) {//todo: fix this
        if (!RegisterMenuController.isCorrectUsername(newValue)) {
            usernameErrorLabel.setText(Errors.USERNAME_FORMAT_ERROR.getErrorText());
        }
        else if (RegisterMenuController.isUsernameUsed(newValue)) {
            usernameErrorLabel.setText(Errors.USERNAME_EXIST.getErrorText());
        }
        else {
            usernameErrorLabel.setText("");
        }
    }

    private TextField usernameField(double width, double height) {
        TextField username = new TextField();
        username.setPromptText("Username");
        username.setLayoutX(width / 2 - 100);
        username.setLayoutY(height / 2 - 100);
        usernameErrorLabel.setTextFill(Color.RED);
        usernameErrorLabel.setLayoutX(width / 2 - 250);
        usernameErrorLabel.setLayoutY(height / 2 - 100);
        usernameErrorLabel.setPrefSize(300, 30);
        username.textProperty().addListener((observable, oldValue, newValue) -> {
            usernameValidate(newValue);
        });
        return username;
    }

    private PasswordField passwordField(double width, double height) {
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setLayoutX(width / 2 - 100);
        password.setLayoutY(height / 2 - 50);
        randomPassword.setLayoutX(width / 2 );
        randomPassword.setLayoutY(height / 2 - 25);
        randomPassword.setPrefSize(100, 30);
        randomPassword.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Random Password");
            alert.setHeaderText("Your random password is:");
            alert.setContentText(RegisterMenuController.generateRandomPassword());
            alert.showAndWait();
        });
        checkBox.setLayoutX(width / 2 + 100);
        checkBox.setLayoutY(height / 2 - 50);
        checkBox.setOnMouseClicked(event -> {
            password.setDisable(checkBox.isSelected());
            password.setPromptText(checkBox.isSelected() ? password.getText() : "Password");
        });

        return password;
    }

    private TextField emailField(double width, double height) {
        TextField email = new TextField();
        email.setPromptText("Email");
        email.setLayoutX(width / 2 - 100);
        email.setLayoutY(height / 2);
        return email;
    }

    private TextField nicknameField(double width, double height) {
        TextField nickname = new TextField();
        nickname.setPromptText("Nickname");
        nickname.setLayoutX(width / 2 - 100);
        nickname.setLayoutY(height / 2 + 50);
        return nickname;
    }
}
//    public void run(Scanner scanner) {
//        System.out.println("Welcome to register menu!");
//        String input;
//        Matcher userCreate;
//        RegisterMenuController controller = new RegisterMenuController();
//        while (true) {
//            input = scanner.nextLine();
//            userCreate = RegisterMenuCommands.getMatcher(input, RegisterMenuCommands.USER_CREATE);
//
//            if (userCreate.find()) {
//                if (UserCreate(scanner, userCreate, controller)) return;
//            } else if (input.equalsIgnoreCase("back")) {
//                return;
//            } else {
//                System.out.println("Invalid command!");
//            }
//        }
//    }
//
//    private static boolean UserCreate(Scanner scanner, Matcher userCreate, RegisterMenuController controller) {
//        boolean chooseSuggestedUsername = false;
//        if (!checkEmptyFields(userCreate)) {
//            return false;
//        }
//        String username = userCreate.group("username");
//        String password;
//        String passwordRepeat;
//        String email = userCreate.group("email");
//        String nickname = userCreate.group("nickname");
//        String slogan = "";
//        if (userCreate.group("slogan") != null) {
//            slogan = userCreate.group("slogan");
//            if (slogan.equals("random")) {
//                slogan = RegisterMenuController.generateRandomSlogan();
//            }
//        }
//        if (!userCreate.group("password").equalsIgnoreCase("random")) {
//            password = userCreate.group("password");
//            passwordRepeat = userCreate.group("passwordRepeat");
//        } else {
//            password = RegisterMenuController.generateRandomPassword();
//            passwordRepeat = password;
//        }
//        String output = controller.register(username, password, passwordRepeat, email, nickname, slogan);
//        System.out.println(output);
//        if (output.equals("A player with this username already exist! but you might find these interesting:")) {
//            chooseSuggestedUsername = isChooseSuggestedUsername(scanner, controller, chooseSuggestedUsername, username, password, passwordRepeat, email, nickname, slogan);
//        }
//        if (chooseSuggestedUsername) {
//            output = "register successfully!";
//        }
//        if (output.equals("register successfully!")) {
//            return register(scanner, userCreate, controller, password, slogan);
//        }
//        return false;
//    }
//
//    private static boolean checkEmptyFields(Matcher userCreate) {
//        if (userCreate.group("username").isEmpty()) {
//            System.out.println("Username can't be empty");
//            return false;
//        }
//        if (userCreate.group("password").isEmpty()) {
//            System.out.println("Password can't be empty");
//            return false;
//        }
//        if (userCreate.group("passwordRepeat").isEmpty()) {
//            System.out.println("Password confirmation can't be empty");
//            return false;
//        }
//        if (userCreate.group("email").isEmpty()) {
//            System.out.println("Email can't be empty");
//            return false;
//        }
//        if (userCreate.group("nickname").isEmpty()) {
//            System.out.println("Nickname can't be empty");
//            return false;
//        }
//        return true;
//    }
//
//    private static boolean register(Scanner scanner, Matcher userCreate, RegisterMenuController controller, String password, String slogan) {
//        Matcher questionPick;
//        String input;
//        if (userCreate.group("password").equalsIgnoreCase("random")) {
//            System.out.println("This is your password: " + password);
//            System.out.println("Please confirm it: ");
//            while (true) {
//                input = scanner.nextLine();
//                if (!password.equals(input)) {
//                    System.out.println("The confirmation doesn't match the password, please try again");
//                } else {
//                    System.out.println("Password confirmed successfully");
//                    break;
//                }
//            }
//        }
//        if (userCreate.group("slogan") != null && userCreate.group("slogan").equals("random")) {
//            System.out.println("This is your slogan:");
//            System.out.println(slogan);
//        }
//        System.out.print(controller.showQuestions());
//        while (true) {
//            input = scanner.nextLine();
//            questionPick = RegisterMenuCommands.getMatcher(input, RegisterMenuCommands.QUESTION_PICK);
//            if (questionPick.find()) {
//                if (QuestionPick(scanner, controller, questionPick)) return true;
//            } else if (input.equals("back")) {
//                break;
//            } else {
//                System.out.println("Please answer the question");
//            }
//        }
//        return false;
//    }
//
//    private static boolean QuestionPick(Scanner scanner, RegisterMenuController controller, Matcher questionPick) {
//        if (questionPick.group("questionNumber").isEmpty()) {
//            System.out.println("Question number can't be empty");
//            return false;
//        }
//        if (questionPick.group("answer").isEmpty()) {
//            System.out.println("Answer can't be empty");
//            return false;
//        }
//        if (questionPick.group("answerConfirm").isEmpty()) {
//            System.out.println("Answer confirmation can't be empty");
//            return false;
//        }
//        int number;
//        try {
//            number = Integer.parseInt(questionPick.group("questionNumber"));
//        } catch (NumberFormatException e) {
//            System.out.println("Invalid question number");
//            return false;
//        }
//        String answer = questionPick.group("answer");
//        String answerConfirm = questionPick.group("answerConfirm");
//        String show = controller.securityQuestionErrors(number, answer, answerConfirm);
//        System.out.println(show);
//        if (show.equals("question and answer selected!")) {
//            while (true) {
//                if (showCaptcha(scanner, controller, number, answer)) return true;
//            }
//        }
//        return false;
//    }
//
//    private static boolean showCaptcha(Scanner scanner, RegisterMenuController controller, int number, String answer) {
//        String input;
//        Captcha captcha = new Captcha();
//        System.out.println("please enter the captcha to finish registering");
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
//                System.out.print(captcha.getCreatedCaptcha()[j][i]);
//            }
//            System.out.println();
//        }
//        input = scanner.nextLine();
//        int A = 0;
//        if (input.length() != captcha.getCreatedCaptcha().length) {
//            System.out.println("incorrect captcha, please try again");
//            return false;
//        }
//        for (int i = 0; i < captcha.getCreatedCaptcha().length; i++) {
//            if (input.charAt(i) - '0' != captcha.getNumbers()[i]) {
//                System.out.println("incorrect captcha, please try again");
//                A++;
//                break;
//            }
//        }
//        if (A == 0) {
//            System.out.println("Account created successfully");
//            controller.addUser(answer, number);
//            return true;
//        }
//        return false;
//    }
//
//    private static boolean isChooseSuggestedUsername(Scanner scanner, RegisterMenuController controller, boolean chooseSuggestedUsername, String username, String password, String passwordRepeat, String email, String nickname, String slogan) {
//        String input;
//        String[] toAdds = new String[3];
//        toAdds[0] = "king";
//        toAdds[1] = "007";
//        toAdds[2] = "haj";
//        for (int i = 0; i < 3; i++) {
//            System.out.println((i + 1) + ")" + toAdds[i] + username);
//        }
//        first:
//        while (true) {
//            System.out.println("please choose your favorite username.If you don't have one please type in anything else");
//            input = scanner.nextLine();
//            if (input.equals("1") || input.equals("2") || input.equals("3")) {
//                for (int i = 1; i < 4; i++) {
//                    if (Integer.parseInt(input) == i) {
//                        String answer = controller.register(toAdds[i - 1] + username, password, passwordRepeat, email, nickname, slogan);
//                        System.out.println(answer);
//                        if (answer.equals("register successfully!")) {
//                            chooseSuggestedUsername = true;
//                            System.out.println("This is your username: " + toAdds[i - 1] + username);
//                            break first;
//                        }
//                    }
//                }
//            }
//        }
//        return chooseSuggestedUsername;
//    }
