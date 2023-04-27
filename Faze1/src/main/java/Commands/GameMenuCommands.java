package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    CHOOSE_COLOR("^choose color -c (?<color>\\S+)$"),// do we need this or what ?
    CHOOSE_KEEP ("^choose keep -n (?<number>\\d)$"),// we only need one digit for keep location
    CHANGE_COLOR ("^change color -c (?<color>\\S+)$"),
    CHANGE_KEEP ("^change keep -n (?<number>\\d)$"),
    DROP_BUILDING("(?=.*-x)(?=.*-y)(?=.*-t)^dropbuilding(( -x (?<xCoordinate>[-]?\\d+))|( -y (?<yCoordinate>[-]?\\d+))|( -t (?<type>\\S+))){3}$"),
    DROP_UNIT("(?=.*-x)(?=.*-y)(?=.*-t)(?=.*-c)^dropunit(( -x (?<xCoordinate>[-]?\\d+))|( -y (?<yCoordinate>[-]?\\d+))|( -t (?<type>\\S+))|( -c (?<count>[-]?\\d+))){4}$");// maybe if the number is 0(-1) we remove the keep
    private String regex;

    private GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
