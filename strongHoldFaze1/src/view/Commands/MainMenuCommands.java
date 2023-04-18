package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    ENTER_TO_MAP_MENU(""),
    ENTER_TO_PROFILE_MENU(""),
    START_GAME(""),
    LOGOUT("");
    private String regex;

    private MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
