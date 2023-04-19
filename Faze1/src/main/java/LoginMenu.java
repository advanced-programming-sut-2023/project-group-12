import Commands.LoginMenuCommands;

import java.util.regex.Matcher;

public class LoginMenu {
    public void run() {
        LoginMenuController controller = new LoginMenuController();
        String input;
        Matcher userLogin, forgotMyPassword;
        while (true) {
            input = Main.scanner.nextLine();
            userLogin = LoginMenuCommands.getMatcher(input, LoginMenuCommands.USER_LOGIN);
            forgotMyPassword = LoginMenuCommands.getMatcher(input, LoginMenuCommands.FORGOT_MY_PASSWORD);
            if (userLogin.find()) {
                System.out.println(controller.login(userLogin));
                if (controller.login(userLogin).equals("now you must answer the captcha")) {
                    Captcha captcha = new Captcha();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < captcha.getCreatedCaptcha().length; j++) {
                            System.out.print(captcha.getCreatedCaptcha()[j][i]);
                        }
                        System.out.println();
                    }
                    System.out.println("type in the captcha");
                    input = Main.scanner.nextLine();
                    System.out.println(controller.isCaptchaCorrect(captcha, input));
                    if (controller.isCaptchaCorrect(captcha, input).equals("user logged out successfully!")) {
                        //TODO: create a main menu and send this fucking user to it
                        MainMenu menu = new MainMenu();
                        menu.run();
                        return;
                    }
                }
            }
            else if (forgotMyPassword.find()) {
                System.out.println(controller.forgotMyPassword(forgotMyPassword));
            }
            else if (input.equals("back")) {
                return;
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
