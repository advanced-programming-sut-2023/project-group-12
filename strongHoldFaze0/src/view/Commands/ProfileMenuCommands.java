package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    CHANGE_PASSWORD(""),
    CHANGE_USERNAME(""),
    CHANGE_NICKNAME(""),
    CHANGE_EMAIL(""),
    CHANGE_SLOGAN(""),
    DISPLAY_HIGH_SCORE(""),
    DISPLAY_RANK(""),
    DISPLAY_SLOGAN(""),
    DISPLAY_ALL(""),
    EXIT("");
    private String regex;

    private ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
