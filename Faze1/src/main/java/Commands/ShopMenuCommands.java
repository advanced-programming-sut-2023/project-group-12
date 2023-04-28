package Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    SHOW_PRICE_LIST ("^show price list$"),
    //TODO: figure what to do with these two
    BUY_AND_SELL ("(?=.*-i)(?=.*-a)^(?<action>buy|sell)(( -i (?<name>(\"[^\"]+\")|\\S+))|( -a (?<number>[-]?\\d+))){2}$");
    private String regex;

    private ShopMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ShopMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}