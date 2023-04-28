package view;

import Commands.RegisterMenuCommands;
import controller.RegisterMenuController;
import model.Captcha;
import model.UserDatabase;

import java.util.Scanner;
import java.util.regex.Matcher;

public class RegisterMenu {
    public void run(Scanner scanner) {
        String input;
        Matcher userCreate, questionPick, user_create_with_random_password;
        RegisterMenuController controller = new RegisterMenuController();
        while (true) {
            input = scanner.nextLine();
            userCreate = RegisterMenuCommands.getMatcher(input, RegisterMenuCommands.USER_CREATE);
            user_create_with_random_password = RegisterMenuCommands.getMatcher(input, RegisterMenuCommands.USER_CREATE);
            // todo: add show current menu
            if (userCreate.find()) {
                // todo : handle empty fields
                boolean chooseSuggestedUsername = false;
                String username = userCreate.group("username");
                String password;
                String passwordRepeat;
                String email = userCreate.group("email");
                String nickname = userCreate.group("nickname");
                String slogan = "";
                if (userCreate.group("slogan") != null) {
                    slogan = userCreate.group("slogan");
                    if (slogan.equals("random")) {
                        slogan = RegisterMenuController.generateRandomSlogan();
                    }
                }
                if (userCreate.group("password") != null) {
                    password = userCreate.group("password");
                    passwordRepeat = userCreate.group("passwordRepeat");
                }
                else {
                    password = RegisterMenuController.generateRandomPassword();
                    passwordRepeat = password;
                }
                String output = controller.register(username, password, passwordRepeat, email, nickname, slogan);
                System.out.println(output);
                if (output.equals("A player with this username already exist! but you might find these interesting:")) {
                    String[] toAdds = new String[3];
                    toAdds[0] = "king";
                    toAdds[1] = "007";
                    toAdds[2] = "haj";
                    for (int i = 0; i < 3; i++) {
                        System.out.println((i + 1) + ")" + toAdds[i] + username);
                    }
                    System.out.println("please choose your favorite username.If you don't have one please type in anything else");
                    input = scanner.nextLine();
                    if (input.equals("1") || input.equals("2") || input.equals("3")) {
                        for (int i = 1; i < 4; i++) {
                            if (Integer.parseInt(input) == i) {
                                String answer = controller.register(toAdds[i] + username, password, passwordRepeat, email, nickname, slogan);
                                if (answer.equals("register successfully!")) {
                                    chooseSuggestedUsername = true;
                                }
                            }
                        }
                    }
                }
                if (output.equals("register successfully!") || chooseSuggestedUsername) {
                    if (userCreate.group("random")!= null) {
                        System.out.println("This is your password: " + password);
                        System.out.println("Please confirm it: ");
                        while (true) {
                            input = scanner.nextLine();
                            if (!input.equals(password)) {
                                System.out.println("The confirmation doesn't match the password, please try again");
                            }
                            else if (input.equals(password)) {
                                System.out.println("Password confirmed successfully");
                                break;
                            }
                        }
                    }
                    if (userCreate.group("slogan")!= null && userCreate.group("slogan").equals("random")) {
                        System.out.println("This is your slogan:");
                        System.out.println(slogan);
                    }
                    System.out.print(controller.showQuestions());
                    while (true) {
                        input = scanner.nextLine();
                        questionPick = RegisterMenuCommands.getMatcher(input, RegisterMenuCommands.QUESTION_PICK);
                        if (questionPick.find()) {
                            int number = Integer.parseInt(questionPick.group("questionNumber"));
                            String answer = questionPick.group("answer");
                            String answerConfirm = questionPick.group("answerConfirm");
                            String show = controller.securityQuestionErrors(number, answer, answerConfirm);
                            System.out.println(show);
                            if (show.equals("question and answer selected!")) {
                                while (true) {
                                    Captcha captcha = new Captcha();
                                    System.out.println("please enter the captcha to finish registering");
                                    for (int i = 0; i < 8; i++) {
                                        for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
                                            System.out.print(captcha.getCreatedCaptcha()[j][i]);
                                        }
                                        System.out.println();
                                    }
                                    input = scanner.nextLine();
                                    int A = 0;
                                    for (int i = 0; i < captcha.getCreatedCaptcha().length; i++) {
                                        if (input.charAt(i) - '0' != captcha.getNumbers()[i]) {
                                            System.out.println("incorrect captcha, please try again");
                                            A++;
                                            break;
                                        }
                                    }
                                    if (A == 0) {
                                        System.out.println("Account created successfully");
                                        controller.addUser(answer,number);
                                        return;
                                    }
                                }
                            }
                            break;
                        }
                        else if (input.equals("back")) {
                            break;
                        }
                        else {
                            System.out.println("Please answer the question");
                        }
                    }
                }
            } else if (input.equals("back")) {
                return;
            } else {
                System.out.println("Invalid command!");
            }
        }
    }
}
