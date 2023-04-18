package view;

import controller.ProfileController;
import model.UserDatabase;

import java.util.regex.Matcher;

public class ProfileMenu {

    private ProfileController controller;


    public void run() {
        controller = new ProfileController(UserDatabase.getCurrentUser());
        while (true) {

        }

    }

    private void changeUsername(Matcher matcher) {
        System.out.println(controller.changeUsername(matcher.group()));
    }

    private void ChangeNickname(Matcher matcher) {
        System.out.println(controller.changeNickname(matcher.group()));
    }

    private void changePassword(Matcher matcher) {
        System.out.println(controller.changePassword(matcher.group()));
    }

    private void changeEmail(Matcher matcher) {
        System.out.println(controller.changeEmail(matcher.group()));
    }

    private void changeSlogan(Matcher matcher) {
        System.out.println(controller.changeSlogan(matcher.group()));
    }

    private void displayHighScore() {
        System.out.println(controller.displayHighScore());
    }

    private void displayRank() {
        System.out.println(controller.displayRank());
    }

    private void displaySlogan() {
        System.out.println(controller.displaySlogan());
    }

    private void displayAll() {
        System.out.println(controller.displayAll());
    }
}
