package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum SignupLoginMenuCommands {
    REGISTER(""),
    LOGIN(""),
    FORGOT_PASSWORD("");
    private String regex;

    private SignupLoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, SignupLoginMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
