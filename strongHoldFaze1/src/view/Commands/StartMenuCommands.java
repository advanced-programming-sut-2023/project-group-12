package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StartMenuCommands {
    REGISTER("");
    private String regex;

    private StartMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, StartMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
