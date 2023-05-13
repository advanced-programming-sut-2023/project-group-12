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
                System.out.println(controller.showAllTrades());
            }
            else if (trade.find()) {
                System.out.println(controller.trade(trade.group("type"), Integer.parseInt(trade.group("amount")), Integer.parseInt(trade.group("price")), trade.group("message"), trade.group("receiverName")));
            }
            else if (accept.find()) {
                System.out.println(controller.acceptTrade(Integer.parseInt(accept.group("id")), accept.group("message")));
            }
            else if (tradeHistory.find()) {
                System.out.println(controller.showTradeHistory());
            }
            else if (input.equals("back")) {
                controller.deleteTrades();
                return;
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
