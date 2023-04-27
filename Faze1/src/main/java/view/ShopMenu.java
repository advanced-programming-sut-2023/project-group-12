package view;

import Commands.ShopMenuCommands;
import controller.ShopMenuController;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    public void run(Scanner scanner) {
        String input;
        Matcher showPriceList, buyAndSell;
        ShopMenuController controller = new ShopMenuController();
        while (true) {
            input = scanner.nextLine();
            showPriceList = ShopMenuCommands.getMatcher(input, ShopMenuCommands.SHOW_PRICE_LIST);
            buyAndSell = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_AND_SELL);
            if (showPriceList.find()) {

            }
            else if (buyAndSell.find()) {

            }
            else if (input.equals("back")) {

            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
