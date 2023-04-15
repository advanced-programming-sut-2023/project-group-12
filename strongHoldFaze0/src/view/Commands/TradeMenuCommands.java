package view.Commands;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TradeMenuCommands {
    TRADING(""),
    SHOW_TRADE_LIST(""),
    TRADE_ACCEPT(""),
    SHOW_TRADE_HISTORY(""),
    EXIT("");
    private String regex;

    TradeMenuCommands(String regex) {
        this.regex = regex;
    }

    public static Matcher getMatcher(String input, TradeMenuCommands command) {
        return Pattern.compile(command.regex).matcher(input);
    }
}
