package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    //todo : should we have some keeps ready for people or should we let them have their own ?
    CHOOSE_COLOR("^choose color -c (?<color>\\S+)$"),// do we need this or what ?
    CHOOSE_KEEP("^choose keep -n (?<number>\\d)$"),// we only need one digit for keep location
    CHANGE_COLOR("^change color -c (?<color>\\S+)$"),
    CHANGE_KEEP("^change keep -n (?<number>\\d)$"),
    SHOW_POPULARITY_FACTORS("^show popularity factors$"),
    SHOW_POPULARITY("^show popularity$"),
    SHOW_FOOD_LIST("^show food list$"),
    SET_FOOD_RATE("^food rate -r (?<rate>[-]?\\d*)$"),
    SHOW_FOOD_RATE("^food rate show$"),
    SET_TAX_RATE("^tax rate -r (?<rate>[-]?\\d*)$"),
    SHOW_TAX_RATE("^tax rate show$"),
    SET_FEAR_RATE("^fear rate -r (?<rate>[-]?\\d*)$"),
    ;// maybe if the number is 0(-1) we remove the keep
    private String regex;

    private GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
