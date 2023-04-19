package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    USER_CREATE("(?=.*-p)(?=.*-u)(?=.*--email)(?=.*-u)(?=.*-s)?^user create(( -u (?<username>(\"[^\"]+\")|\\S+))|( -p (?<password>(\"[^\"]+\")|\\S+) (?<passwordRepeat>(\"[^\"]+\")|\\S+))|( -s (?<slogan>(\"[^\"]+\")|\\S+))|( --email (?<email>(\"[^\"]+\")|\\S+))|( -n (?<nickname>(\"[^\"]+\")|\\S+))){4,5}$"),
    QUESTION_PICK("(?=.*-q)(?=.*-a)(?=.*-c)^question pick(( -q (?<questionNumber>[-]?\\d+))|( -a (?<answer>(\"[^\"]+\")|\\S+))|( -c (?<answerConfirm>(\"[^\"]+\")|\\S+))){3}$"),
    CAPTCHA_READER("^(?<captcha>[-]?\\d+)$");
    private String regex;

    private RegisterMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RegisterMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
