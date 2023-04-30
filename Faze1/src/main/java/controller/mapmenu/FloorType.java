package controller.mapmenu;

import model.map.Map;
import model.map.Type;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum FloorType {
    EARTH("^earth$", Type.EARTH),
    GRAVEL("^gravel$", Type.GRAVEL),
    STONE_MINE("^stoneMine$", Type.STONE_MINE),
    ROCK_NORTH("^rockN$", Type.ROCK_NORTH),
    ROCK_EAST("^rockE$", Type.ROCK_EAST),
    ROCK_WEST("^rockW$", Type.ROCK_WEST),
    ROCK_SOUTH("^rockS$", Type.ROCK_SOUTH),
    IRON_MINE("^ironMine$", Type.IRON_MINE),
    GRASS("^grass$", Type.GRASS),
    MEADOW("^meadow$", Type.MEADOW),
    DENSE_MEADOW("^denseMeadow$", Type.DENSE_MEADOW),


    OIL("^oil$", Type.OIL),
    PLAIN("^plain$", Type.PLAIN),
    SHALLOW_WATER("^shallowWater$", Type.SHALLOW_WATER),
    FOUNTAIN("^fountain$", Type.FOUNTAIN),
    SMALL_POND("^smallPond$", Type.SMALL_POND),
    BIG_POND("^bigPond$", Type.BIG_POND),
    BEACH("^beach$", Type.BEACH),
    SEA("^sea$", Type.SEA);

    private String regex;

    private Type type;


    FloorType(String regex, Type type) {
        this.regex = regex;
        this.type = type;
    }

    public static boolean hasMatcher(String string, FloorType floorType) {
        if (Pattern.compile(floorType.regex).matcher(string).find())
            return true;
        return false;
    }

    public Type getType() {
        return type;
    }
}
