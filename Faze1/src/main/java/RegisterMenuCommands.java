import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum RegisterMenuCommands {
    USER_CREATE("^user\\s+create(?=(?:\\s+[-]{1,2}[a-z]+(?:\\s+\\S+)(\\s+[^-^\\s]+)?)(?:\\s+[-]{1,2}[a-z]+(?:\\s+\\S+)(\\s+[^-^\\s]+)?)(?:\\s+[-]{1,2}[a-z]+(?:\\s+\\S+)(\\s+[^-^\\s]+)?)(?:\\s+[-]{1,2}[a-z]+(?:\\s+\\S+)(\\s+[^-^\\s]+)?)(?:\\s+[-]{1,2}[a-z]+(?:\\s+\\S+)(\\s+[^-^\\s]+)?)?)"),
    QUESTION_PICK(""),
    CAPTCHA_READER("^\\d+$");
    private String regex;

    private RegisterMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, RegisterMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
