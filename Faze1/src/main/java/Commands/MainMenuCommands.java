package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MainMenuCommands {
    GO_TO_MAP_MENU("^go to map menu -size (?<size>\\d+)$"),
    GO_TO_PROFILE_MENU("^go to profile menu$"),
    GO_TO_START_MENU("^go to start menu$"),
    USER_LOGOUT("^user logout$");
    private String regex;

    private MainMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MainMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
