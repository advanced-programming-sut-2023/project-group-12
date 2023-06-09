package view;

import Commands.LoginMenuCommands;
import controller.LoginMenuController;
import model.Captcha;
import model.UserDatabase;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private int numberOfWrongPasswords = 0;

    public void run(Scanner scanner) throws NoSuchAlgorithmException {
        LoginMenuController controller = new LoginMenuController();
        String input;
        Matcher userLogin, forgotMyPassword;
        while (true) {
            input = scanner.nextLine();
            userLogin = LoginMenuCommands.getMatcher(input, LoginMenuCommands.USER_LOGIN);
            forgotMyPassword = LoginMenuCommands.getMatcher(input, LoginMenuCommands.FORGOT_MY_PASSWORD);
            if (userLogin.find()) {
                if (UserLogin(scanner, controller, userLogin)) return;
            } else if (forgotMyPassword.find()) {
                if (ForgotMyPassword(scanner, controller, input, forgotMyPassword)) return;
            } else if (input.equalsIgnoreCase("back")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }

    private boolean UserLogin(Scanner scanner, LoginMenuController controller, Matcher userLogin) throws NoSuchAlgorithmException {
        if (userLogin.group("username").isEmpty()) {
            System.out.println("Username can't be empty");
            return false;
        }
        if (userLogin.group("password").isEmpty()) {
            System.out.println("Password can't be empty");
            return false;
        }
        String username = userLogin.group("username");
        String password = userLogin.group("password");
        boolean stayLoggedIn = userLogin.group("stay") != null;
        String output = controller.login(username, password);
        System.out.println(output);
        if (output.equals("Username and password didn't match!")) {
            return delay();
        } else if (output.equals("now you must answer the captcha")) {
            while (true) {
                if (showCaptcha(scanner, controller, username, stayLoggedIn)) return true;
            }
        }
        return false;
    }

    private boolean delay() {
        numberOfWrongPasswords++;
        if (numberOfWrongPasswords <= 3) {
            System.out.println("Please wait " + numberOfWrongPasswords * 5 + " seconds.");
            for (int i = 0; i < 5 * numberOfWrongPasswords; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(5 * numberOfWrongPasswords - i);
            }
        } else {
            System.out.println("Too many attempts, Please try again later");
            return true;
        }
        return false;
    }

    private static boolean showCaptcha(Scanner scanner, LoginMenuController controller, String username, boolean stayLoggedIn) throws NoSuchAlgorithmException {
        String output;
        String input;
        Captcha captcha = new Captcha();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
                System.out.print(captcha.getCreatedCaptcha()[j][i]);
            }
            System.out.println();
        }
        System.out.println("Please enter the captcha");
        input = scanner.nextLine();
        if (input.isEmpty()) {
            System.out.println("captcha can't be empty");
            return false;
        }
        output = controller.isCaptchaCorrect(captcha, input);
        System.out.println(output);
        if (output.equals("user logged in successfully!")) {
            UserDatabase.setCurrentUser(UserDatabase.getUserByUsername(username));
            UserDatabase.getUserByUsername(username).setStayLoggedIn(stayLoggedIn);
            UserDatabase.saveUsers();
            MainMenu menu = new MainMenu();
            menu.run(scanner);
            return true;
        }
        return false;
    }

    private static boolean ForgotMyPassword(Scanner scanner, LoginMenuController controller, String input, Matcher forgotMyPassword) throws NoSuchAlgorithmException {
        if (forgotMyPassword.group("username").isEmpty()) {
            System.out.println("Username can't be empty");
            return false;
        }
        String output = controller.checkUsername(forgotMyPassword.group("username"));
        System.out.println(output);
        if (output.equals("The username format is incorrect.") || output.equals("User with this username doesn't exist.")) {
            return false;
        }
        if (output.equals("Please answer your security question:\n" + UserDatabase.getUserByUsername(forgotMyPassword.group("username")).getQuestion())) {
            while (true) {
                input = scanner.nextLine();
                output = controller.checkAnswer(forgotMyPassword.group("username"), input);
                System.out.println(output);
                if (output.equals("Correct. Please set your new password.")) {
                    Matcher newPassword;
                    while (true) {
                        input = scanner.nextLine();
                        newPassword = LoginMenuCommands.getMatcher(input, LoginMenuCommands.NEW_PASSWORD);
                        if (newPassword.find()) {
                            if (newPassword.group("password").isEmpty()) {
                                System.out.println("Password can't be empty");
                                continue;
                            }
                            if (newPassword.group("passwordRepeat").isEmpty()) {
                                System.out.println("Password can't be empty");
                                continue;
                            }
                            output = controller.setNewPassword(forgotMyPassword.group("username"), newPassword.group("password"), newPassword.group("passwordRepeat"));
                            System.out.println(output);
                            if (hasPasswordChanged(output)) {
                                while (true) {
                                    showCaptcha(scanner, controller, forgotMyPassword.group("username"), false);
                                }
                            }
                        } else {
                            System.out.println("Invalid command!");
                        }
                    }
                }
            }
        } else if (input.equals("back")) {
            return true;
        } else {
            System.out.println("Invalid command!");
        }
        return false;
    }

    private static boolean hasPasswordChanged(String output) {
        return output.contains("password changed successfully.");
    }
}
