package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum GameMenuCommands {
    //todo : should we have some keeps ready for people or should we let them have their own ?
    CHOOSE_COLOR("^choose color -c (?<color>\\S+)$"),// do we need this or what ?
    CHOOSE_KEEP("^choose keep -n (?<number>\\d)$"),// we only need one digit for keep location
    CHANGE_COLOR("^change color -c (?<color>\\S+)$"),
    CHANGE_KEEP("^change keep -n (?<number>\\d)$"),// if the number is 1 remove the keep
    SHOW_POPULARITY_FACTORS("^show popularity factors$"),
    SHOW_POPULARITY("^show popularity$"),
    SHOW_FOOD_LIST("^show food list$"),
    SET_FOOD_RATE("^food rate -r (?<rate>[-]?\\d*)$"),
    SHOW_FOOD_RATE("^food rate show$"),
    SET_TAX_RATE("^tax rate -r (?<rate>[-]?\\d*)$"),
    SHOW_TAX_RATE("^tax rate show$"),
    SET_FEAR_RATE("^fear rate -r (?<rate>[-]?\\d*)$"),
    DROP_BUILDING("(?=.*-x)(?=.*-y)(?=.*-type)^dropbuilding(( -x (?<xCoordinate>[-]?\\d*))|( -y (?<yCoordinate>[-]?\\d*))|( -type (?<type>\\S+|(\"[^\"]*\")))){3}$"),
    SELECT_BUILDING("(?=.*-x)(?=.*-y)^select building(( -x (?<xCoordinate>[-]?\\d*))|( -y (?<yCoordinate>[-]?\\d*))){2}$"),
    CREATE_UNIT("(?=.*-type)(?=.*-count)^createunit(( -type (?<type>\\S+|(\"[^\"]*\")))|( -count (?<count>[-]?\\d*))){2}$"),
    REPAIR("^repair$"),
    SELECT_UNIT("(?=.*-x)(?=.*-y)^select unit(( -x (?<xCoordinate>[-]?\\d*))|( -y (?<yCoordinate>[-]?\\d*))){2}$"),
    MOVE_UNIT("(?=.*-x)(?=.*-y)^move unit to(( -x (?<xCoordinate>[-]?\\d*))|( -y (?<yCoordinate>[-]?\\d*))){2}$"),
    PATROL_UNIT("(?=.*-x1)(?=.*-y1)(?=.*-x2)(?=.*-y2)^patrol unit(( -x1 (?<x1Coordinate>[-]?\\d*))|( -y1 (?<y1Coordinate>[-]?\\d*))|( -x2 (?<x2Coordinate>[-]?\\d*))|( -y2 (?<y2Coordinate>[-]?\\d*))){4}$"),
    SET_MODE("(?=.*-x)(?=.*-y)(?=.*-s)^set(( -x (?<xCoordinate>[-]?\\d*))|( -y (?<yCoordinate>[-]?\\d*))|( -s (?<mode>(standing|defensive|offensive)))){3}$"),// defensive offensive standing
    GROUND_ATTACK("^attack -e (?<xCoordinate>[-]?)\\d*) (?<yCoordinate>[-]?\\d*)$"),
    AIR_ATTACK("(?=.*-x)(?=.*-y)^attack(( -x (?<xCoordinate>[-]?\\d*))|( -y (?<yCoordinate>[-]?\\d*))){2}$"),
    POUR_OIL("^pour oil -d (?<direction>[news]?)$"),
    DIG_TUNNEL("(?=.*-x)(?=.*-y)^dig tunnel(( -x (?<xCoordinate>[-]?\\d*))|( -y (?<yCoordinate>[-]?\\d*))){2}$"),
    BUILD_EQUIPMENT("^build -q (?<equipmentName>\\S+|(\"[^\"]*\"))$"),
    DISBAND_UNIT("^disband unit$"),
    // DROP_GATE(""),
    //BUILD_WALL(""),
    //PUT_STAIRS(""),
    //DITCH("dig, cancel digging, remove ditch"),
    //BURN OIL("")
    //HANDLE PAGE 26 OF DOC

    ;
    private String regex;

    private GameMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, GameMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
