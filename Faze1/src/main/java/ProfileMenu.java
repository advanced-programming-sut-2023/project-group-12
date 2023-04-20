
import Commands.ProfileMenuCommands;

import java.util.regex.Matcher;

public class ProfileMenu {

    private ProfileController controller;


    public void run() {
        controller = new ProfileController(UserDatabase.getCurrentUser());
        String input = Main.scanner.nextLine();
        Matcher matcher;
        while (true) {
            if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_EMAIL)) != null)
                changeEmail(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_USERNAME)) != null)
                changeUsername(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_NICKNAME)) != null)
                changeNickname(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_SLOGAN)) != null)
                changeSlogan(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_CHANGE_PASSWORD)) != null)
                changePassword(matcher);
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_REMOVE_SLOGAN)) != null)
                removeSlogan();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_HIGH_SCORE)) != null)
                displayHighScore();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_RANK)) != null)
                displayRank();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY_SLOGAN)) != null)
                displaySlogan();
            else if ((matcher = ProfileMenuCommands.getMatcher(input, ProfileMenuCommands.PROFILE_DISPLAY)) != null)
                displayAll();
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
