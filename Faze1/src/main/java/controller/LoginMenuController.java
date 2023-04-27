package controller;

import model.Captcha;
import model.User;
import model.UserDatabase;

import java.util.regex.Matcher;

public class LoginMenuController {
    public String login (String username, String password,boolean stayLoggedIn) {
        if (UserDatabase.getUserByUsername(username) == null){
            return "Username and password didn't match!";
        }
        if (!(UserDatabase.getUserByUsername(username).getPassword().equals(password))) {
            return "Username and password didn't match!";
        }

        return "now you must answer the captcha";
    }
    public String isCaptchaCorrect (Captcha captcha, String input) {
        if (input.equals("new captcha")) {
            return "new captcha is being generated";
        }
        for (int i = 0; i < captcha.getNumbers().length; i++) {
            if (input.charAt(i) - '0' > 9 || input.charAt(i) - '0' < 0) {
                return "incorrect input. The captcha contains digits from 0 to 9";
            }
            if (input.charAt(i) - '0' != captcha.getNumbers()[i]) {
                return "incorrect captcha";
            }
        }
        return "user logged in successfully!";
    }
    public String checkUsername (String username) {
        // empty fields!
        if (!RegisterMenuController.isCorrectUsername(username)) {
            return "The username format is incorrect.";
        }
        else if (UserDatabase.getUserByUsername(username) == null) {
            return "User with this username doesn't exist.";
        }
        User user = UserDatabase.getUserByUsername(username);
       return "Please answer your security question:\n"+user.getQuestion();
    }
    public String checkAnswer (String username, String answer) {
        if (UserDatabase.getUserByUsername(username).getAnswer().equals(answer)) {
            return "Correct. Please set your new password.";
        }
        return "Incorrect answer. please try again";
    }
    public String setNewPassword (String username, String password, String passwordRepeat) {
        if (password.equals("random")) {
            String newPass = RegisterMenuController.generateRandomPassword();
            UserDatabase.getUserByUsername(username).setPassword(newPass);
            return "password changed successfully. Here is your new password:\n" + newPass;
        }
        if (RegisterMenuController.isPasswordWeak(password)) {
            return "Password is weak. Try again.";
        }
        if (!password.equals(passwordRepeat)) {
            return "password and password confirm don't match.";
        }
        UserDatabase.getUserByUsername(username).setPassword(password);
        return "password changed successfully.";
    }
}
