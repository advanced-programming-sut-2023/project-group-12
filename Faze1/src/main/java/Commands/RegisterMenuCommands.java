package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
//    USER_CREATE("^user create(?<userdata>.+)$"),
//    USERNAME ("^.* -u (?<username>(\"[^\"]+\")|\\S+).*$"),
//    PASSWORD ("^.* -p (?<password>(\"[^\"]+\")|\\S+) (?<passwordRepeat>(\"[^\"]+\")|\\S+).*$"),
//    NICKNAME ("^.* -n (?<nickname>(\"[^\"]+\")|\\S+).*$"),
//    EMAIL("^.* --email (?<email>(\"[^\"]+\")|\\S+).*$"),
//    SLOGAN ("^.* -s (?<slogan>(\"[^\"]+\")|\\S+).*$"),
      //TODO: check if there's anything unusual
    USER_CREATE("(?=.*-p)(?=.*-u)(?=.*--email)(?=.*-u)(?=.*-s)?^user create(( -u (?<username>(\"[^\"]+\")|\\S+))|( -p (?<password>(\"[^\"]+\")|\\S+) (?<passwordRepeat>(\"[^\"]+\")|\\S+))|( -s (?<slogan>(\"[^\"]+\")|\\S+))|( --email (?<email>(\"[^\"]+\")|\\S+))|( -n (?<nickname>(\"[^\"]+\")|\\S+))){4,5}$"),
//    QUESTION_PICK("^question pick(?<questionDetails>.+)$"),
//    QUESTION_NUMBER ("^.* -q (?<questionNumber>[-]?\\d+).*$"),
//    ANSWER("^.* -a (?<answer>(\"[^\"]+\")|\\S+)) (?<answerRepeat>(\"[^\"]+\")|\\S+)).*$"),
      //TODO: check if there's anything unusual
    QUESTION_PICK("(?=.*-q)(?=.*-a)(?=.*-c)^question pick(( -q (?<questionNumber>[-]?\\d+))|( -a (?<answer>(\"[^\"]+\")|\\S+))|( -c (?<answerConfirm>(\"[^\"]+\")|\\S+))){3}$"),
    CAPTCHA_READER("^[-]?\\d+$");
    private String regex;

    private RegisterMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RegisterMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
