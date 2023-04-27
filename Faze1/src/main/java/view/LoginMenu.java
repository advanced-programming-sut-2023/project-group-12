package view;

import Commands.LoginMenuCommands;
import controller.LoginMenuController;
import model.Captcha;
import model.UserDatabase;
import view.MainMenu;

import java.util.Scanner;
import java.util.regex.Matcher;

public class LoginMenu {
    private int numberOfWrongPasswords = 0;

    public void run(Scanner scanner) {
        LoginMenuController controller = new LoginMenuController();
        String input;
        Matcher userLogin, forgotMyPassword;
        while (true) {
            input = scanner.nextLine();
            userLogin = LoginMenuCommands.getMatcher(input, LoginMenuCommands.USER_LOGIN);
            forgotMyPassword = LoginMenuCommands.getMatcher(input, LoginMenuCommands.FORGOT_MY_PASSWORD);
            if (userLogin.find()) {
                String username = userLogin.group("username");
                String password = userLogin.group("password");
                boolean stayLoggedIn = false;
                if (userLogin.group("stay") != null) {
                    stayLoggedIn = true;
                }
                String output = controller.login(username, password, stayLoggedIn);
                System.out.println(output);
                if (output.equals("Username and password didn't match!")) {
                    numberOfWrongPasswords++;
                    System.out.println("Please wait " + numberOfWrongPasswords * 5 + " seconds.");
                    if (numberOfWrongPasswords <= 3) {
                        for (int i = 0; i < 5 * numberOfWrongPasswords; i++) {
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                            System.out.println(5 * numberOfWrongPasswords - i);
                        }
                    }
                    else {
                        System.out.println("Too many attempts, Please try again later");
                        return;
                    }
                } else if (controller.login(username, password, stayLoggedIn).equals("now you must answer the captcha")) {
                    while (true) {
                        Captcha captcha = new Captcha();
                        for (int i = 0; i < 8; i++) {
                            for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
                                System.out.print(captcha.getCreatedCaptcha()[j][i]);
                            }
                            System.out.println();
                        }
                        System.out.println("Please enter the captcha");
                        input = scanner.nextLine();
                        String output = controller.isCaptchaCorrect(captcha, input);
                        System.out.println(output);
                        if (output.equals("user logged in successfully!")) {
                            UserDatabase.setCurrentUser(UserDatabase.getUserByUsername(username));
                            MainMenu menu = new MainMenu();
                            menu.run(scanner);
                            return;
                        }
                    }
                }
            } else if (forgotMyPassword.find()) {
                String output = controller.checkUsername(forgotMyPassword.group("username"));
                System.out.println(output);
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
                                    output = controller.setNewPassword(forgotMyPassword.group("username"), newPassword.group("password"), newPassword.group("passwordRepeat"));
                                    System.out.println(output);
                                    if (!output.equals("Password is weak. Try again.") && !output.equals("password and password confirm don't match.")) {
                                        while (true) {
                                            Captcha captcha = new Captcha();
                                            for (int i = 0; i < 8; i++) {
                                                for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
                                                    System.out.print(captcha.getCreatedCaptcha()[j][i]);
                                                }
                                                System.out.println();
                                            }
                                            System.out.println("Please enter the captcha");
                                            input = scanner.nextLine();
                                            output = controller.isCaptchaCorrect(captcha, input);
                                            System.out.println(output);
                                            if (output.equals("user logged in successfully!")) {
                                                UserDatabase.setCurrentUser(UserDatabase.getUserByUsername(forgotMyPassword.group("username")));
                                                MainMenu menu = new MainMenu();
                                                menu.run(scanner);
                                                return;
                                            }
                                        }
                                    } else {
                                        System.out.println("Invalid command!");
                                    }
                                }
                            }
                        }
                    }
                } else if (input.equals("back")) {
                    return;
                } else {
                    System.out.println("Invalid command!");
                }
            } else if (input.equals("back")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
