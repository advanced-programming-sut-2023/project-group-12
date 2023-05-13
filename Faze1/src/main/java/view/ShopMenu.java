package view;

import Commands.ShopMenuCommands;
import controller.ShopMenuController;
import model.Game;

import java.util.Scanner;
import java.util.regex.Matcher;

public class ShopMenu {
    public void run(Scanner scanner, Game game) {
        System.out.println("wellcome to shop menu");
        String input;
        Matcher showPriceList, buyAndSell;
        ShopMenuController controller = new ShopMenuController(game);
        while (true) {
            input = scanner.nextLine();
            showPriceList = ShopMenuCommands.getMatcher(input, ShopMenuCommands.SHOW_PRICE_LIST);
            buyAndSell = ShopMenuCommands.getMatcher(input, ShopMenuCommands.BUY_AND_SELL);
            if (showPriceList.find()) {
                System.out.println(controller.showPriceList());
            }
            else if (buyAndSell.find()) {
                System.out.println(controller.buyOrSell(buyAndSell.group("action"), buyAndSell.group("name"), Integer.parseInt(buyAndSell.group("number"))));
            }
            else if (input.equals("back")) {
                return;
            }
            else {
                System.out.println("Invalid command!");
            }
        }
    }
}
