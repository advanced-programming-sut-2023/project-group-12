package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum MapMenuCommands {
    SET_TEXTURE_OF_CELL ("(?=.*-x)(?=.*-y)(?=.*-t)^settexture(( -x (?<xCoordinate>[-]?\\d+))|( -y (?<yCoordinate>[-]?\\d+))|( -t (?<type>\\S+))){3}$"),
    SET_TEXTURE_OF_CELLS ("(?=.*-x1)(?=.*-y1)(?=.*-x2)(?=.*-y2)(?=.*-t)^settexture(( -x1 (?<x1Coordinate>[-]?\\d+))|( -y1 (?<y1Coordinate>[-]?\\d+))|( -x2 (?<x2Coordinate>[-]?\\d+))|( -y2 (?<y2Coordinate>[-]?\\d+))|( -t (?<type>\\S+))){5}$"),
    CLEAR_CELL ("(?=.*-x)(?=.*-y)^clear(( -x (?<xCoordinate>[-]?\\d+))|( -y (?<yCoordinate>[-]?\\d+))){2}$"),
    DROP_ROCK ("(?=.*-x)(?=.*-y)(?=.*-d)^droprock(( -x (?<xCoordinate>[-]?\\d+))|( -y (?<yCoordinate>[-]?\\d+))|( -d (?<direction>(n|e|w|s|r)))){3}$"),
    DROP_TREE ("(?=.*-x)(?=.*-y)(?=.*-t)^droptree(( -x (?<xCoordinate>[-]?\\d+))|( -y (?<yCoordinate>[-]?\\d+))|( -t (?<type>\\S+))){3}$");
    // TODO: figure out where the other kind of changes in a map should be placed
    private String regex;

    private MapMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, MapMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
