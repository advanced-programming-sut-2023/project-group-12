package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StartMenuCommands {
    // some are for before the game some are for the beginning of the game
    START_GAME ("^start game$"),
    ADD_PLAYER ("^add player -u (?<username>(\"[^\"]+\")|\\S+)$"),
    REMOVE_PLAYER ("^remove player -u (?<username>(\"[^\"]+\")|\\S+)$"),
    REMOVE_ALL_PLAYERS("^remove all players$"),
    ADD_RANDOM_PLAYERS ("^add random players -n (?<number>([-]?\\\\d+)|(random))$"),
    CHOOSE_MAP ("^choose map -n (?<number>([-]?\\d+)|(random))$");
    // maybe we can add show map in here so that the player can see what the map they're choosing looks like
    private String regex;

    private StartMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, StartMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
