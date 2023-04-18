package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum LoginMenuCommands {
//    USER_LOGIN("^user login(?<userdata>.+)$"),
//    USERNAME ("^.* -u (?<username>(\"[^\"]+\")|\\S+).*$"),
//    PASSWORD ("^.* -p (?<password>(\"[^\"]+\")|\\S+) (?<passwordRepeat>(\"[^\"]+\")|\\S+).*$"),
//    STAY_LOGGED_IN("^.*--stay-logged-in.*$"),
    //TODO: check if there's anything unusual
    USER_LOGIN("(?=.*-u)(?=.*-p)(?=.*--stay-logged-in)?^user login(( -u (?<username>(\"[^\"]+\")|\\S+))|( -p (?<password>(\"[^\"]+\")|\\S+) (?<passwordRepeat>(\"[^\"]+\")|\\S+))|(--stay-logged-in)){2,3}"),
    FORGOT_MY_PASSWORD ("^forgot my password -u (?<username>(\"[^\"]+\")|\\S+)$"),
    USER_LOGOUT("^user logout$");
    // answering security question before forgot mu password is handled in register menu commands
    private String regex;

    private LoginMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, LoginMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
