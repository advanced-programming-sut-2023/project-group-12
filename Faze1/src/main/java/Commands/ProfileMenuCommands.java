package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ProfileMenuCommands {
    PROFILE_CHANGE("^profile change(( -u (?<username>(\"[^\"]+\")|\\S+))|( -n (?<nickname>(\"[^\"]+\")|\\S+))|( -e (?<email>(\"[^\"]+\")|\\S+)))$"),
    PROFILE_CHANGE_PASSWORD("(?=.*-o)(?=.*-n)^profile change password(( -o (?<oldPassword>(\"[^\"]+\")|\\S+))|( -n (?<newPassword>(\"[^\"]+\")|\\S+))){2}$"),
    PROFILE_CHANGE_SLOGAN("^profile change slogan -s (?<slogan>(\"[^\"]+\")|\\S+))$"),
    PROFILE_REMOVE_SLOGAN("^Profile remove slogan$"),
    PROFILE_DISPLAY_HIGH_SCORE("^profile display highscore$"),
    PROFILE_DISPLAY_RANK("^profile display rank$"),
    PROFILE_DISPLAY_SLOGAN("^profile display slogan$"),
    PROFILE_DISPLAY("^profile display$");
    private String regex;

    private ProfileMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ProfileMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
