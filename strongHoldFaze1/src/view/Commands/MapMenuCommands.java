package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    NEW_MAP(""),
    SHOW_DETAIL(""),
    SET_TEXTURE(""),
    CLEAR(""),
    DROP_ROCK(""),
    DROP_TREE(""),
    DROP_BUILDING(""),
    DROP_UNIT(""),
    EXIT("");
    private String regex;

    private MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
