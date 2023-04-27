package view;

import Commands.TradeMenuCommands;
import controller.TradeMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class TradeMenu {
    public void run (Scanner scanner) {
        String input;
        Matcher tradeList,trade,accept,tradeHistory;
        TradeMenuController controller = new TradeMenuController();
        while (true) {
            input = scanner.nextLine();
            tradeList = TradeMenuCommands.getMatcher(input,TradeMenuCommands.SHOW_ALL_TRADES);
            tradeHistory = TradeMenuCommands.getMatcher(input,TradeMenuCommands.TRADE_HISTORY);
            trade = TradeMenuCommands.getMatcher(input,TradeMenuCommands.TRADE);
            accept = TradeMenuCommands.getMatcher(input,TradeMenuCommands.TRADE_ACCEPT);
            if (tradeList.find()) {

            }
            else if (trade.find()) {

            }
            else if (accept.find()) {

            }
            else if (tradeHistory.find()) {

            }
            else if (input.equals("back")) {

            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
