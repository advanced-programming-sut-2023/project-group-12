package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
    USER_LOGIN("(?=.*-u)(?=.*-p)(?=.*--stay-logged-in)?^user login(( -u (?<username>(\"[^\"]+\")|\\S+))|( -p (?<password>(\"[^\"]+\")|\\S+) (?<passwordRepeat>(\"[^\"]+\")|\\S+))|(--stay-logged-in)){2,3}"),
    FORGOT_MY_PASSWORD ("^forgot my password -u (?<username>(\"[^\"]+\")|\\S+)$");// this is temporarily here it's for the main menu
    // answering security question before forgot my password is handled in register menu commands
    //captcha reader is also covered in register menu commands
    private String regex;

    private LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
