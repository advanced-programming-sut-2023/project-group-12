package view;

import Commands.ProfileMenuCommands;
import controller.ProfileController;
import model.UserDatabase;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ProfileMenu {
    private ProfileController controller;
    public void run(Scanner scanner) {
       controller = new ProfileController(UserDatabase.getCurrentUser());
        System.out.println("Wellcome to profile menu");
        while (true) {
            String input = scanner.nextLine();
            Matcher matcher;
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_EMAIL)).find())
                changeEmail(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_USERNAME)).find())
                changeUsername(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_NICKNAME)).find())
                changeNickname(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_SLOGAN)).find())
                changeSlogan(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_PASSWORD)).find())
                changePassword(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_REMOVE_SLOGAN)).find())
                removeSlogan();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_HIGH_SCORE)).find())
                displayHighScore();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_RANK)).find())
                displayRank();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_SLOGAN)).find())
                displaySlogan();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY)).find())
                displayAll();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_BACK)).find())
                return;
            else
                System.out.println("Please try again!");

        }
    }


    private void changeUsername(Matcher matcher) {
        System.out.println(controller.changeUsername(matcher.group("username")));
    }

    private void changeNickname(Matcher matcher) {
        System.out.println(controller.changeNickname(matcher.group("nickname")));
    }

    private void changePassword(Matcher matcher) {
        System.out.println(controller.changePassword(matcher.group("oldPassword"), matcher.group("newPassword")));
    }

    private void changeEmail(Matcher matcher) {
        System.out.println(controller.changeEmail(matcher.group("email")));
    }

    private void changeSlogan(Matcher matcher) {
        System.out.println(controller.changeSlogan(matcher.group("slogan")));
    }

    private void removeSlogan() {
        System.out.println(controller.removeSlogan());
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
