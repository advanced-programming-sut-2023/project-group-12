package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum ShopMenuCommands {
    SHOW_PRICE_LIST(""),
    BUY(""),
    SELL(""),
    EXIT("");
    private String regex;

    private ShopMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, ShopMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
