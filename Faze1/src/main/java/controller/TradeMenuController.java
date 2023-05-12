package controller;

import model.Game;
import model.Kingdom;
import model.Property.Food;
import model.Property.Resources;
import model.Property.Weapon;
import model.Trade;

import java.util.ArrayList;

public class TradeMenuController {
    public static String showAllTrades() {

        System.out.println("id \\\\ resource name \\\\ amount \\\\ price \\\\ sender \\\\ receiver \\\\ massage");
        int i = 1;
        for (Trade trade : Game.getYetGame().getCurrentKingdom().getTrades()) {
            System.out.print(i + " \\\\ ");
            if (trade.getProperty() instanceof Food)
                System.out.print(((Food) trade.getProperty()).getType().name().toLowerCase());
            else if (trade.getProperty() instanceof Resources)
                System.out.print(((Resources) trade.getProperty()).getResourceType().name().toLowerCase());
            else if (trade.getProperty() instanceof Weapon)
                System.out.print(((Weapon) trade.getProperty()).getWeaponType().name().toLowerCase());
            System.out.print(" \\\\ " + trade.getResourceAmount() + " \\\\ " + trade.getPrice() +
                    " \\\\ " + trade.getSender().getOwner().getUsername() + " \\\\ " +
                    trade.getReceiver().getOwner().getUsername() + " \\\\ " + trade.getSenderMessage());
        }
        return "";
    }

    public static String trade(String resourceType, int resourceAmount, int price, String message, String receiverName) {
        if (Game.getYetGame().getCurrentKingdom().getPropertyByName(resourceType) != null) {
            if (getUserByNameInThisGame(receiverName) != null) {
                if (resourceAmount > 0) {
                    if (price >= 0) {
                        if (price <= Game.getYetGame().getCurrentKingdom().getGold()) {
                            Game.getYetGame().getCurrentKingdom().setTrades(new Trade(Game.getYetGame().getCurrentKingdom().getPropertyByName(resourceType),
                                    resourceAmount, price, message, getUserByNameInThisGame(receiverName)));
                            getUserByNameInThisGame(receiverName).setTrades(Game.getYetGame().getCurrentKingdom().getTrades().get(Game.getYetGame().getCurrentKingdom().getTrades().size() - 1));
                            return "trade added successfully";
                        } else
                            return "you have not enough money";
                    } else
                        return "wrong price";
                } else
                    return "wrong resource amount";
            } else
                return "User " + receiverName + "isn't in this game";
        } else
            return "there's no " + resourceType;
    }

    public static String acceptTrade(int tradeId, String message) {
        if (tradeId <= Game.getYetGame().getCurrentKingdom().getTrades().size() &&
                tradeId > 0) {
            Game.getYetGame().getCurrentKingdom().getTrades().get(tradeId - 1).doTrade(message);
        } else
            return "wrong trade ID";
    }

    public static String showTradeHistory() {
        System.out.println("resource name \\\\ amount \\\\ price \\\\ sender \\\\ receiver \\\\ is accepted \\\\ sender massage \\\\ receiver message");
        int i = 1;
        for (Trade trade : Game.getYetGame().getCurrentKingdom().getTradesHistory()) {
            System.out.print(i + " \\\\ ");
            if (trade.getProperty() instanceof Food)
                System.out.print(((Food) trade.getProperty()).getType().name().toLowerCase());
            else if (trade.getProperty() instanceof Resources)
                System.out.print(((Resources) trade.getProperty()).getResourceType().name().toLowerCase());
            else if (trade.getProperty() instanceof Weapon)
                System.out.print(((Weapon) trade.getProperty()).getWeaponType().name().toLowerCase());
            System.out.print(" \\\\ " + trade.getResourceAmount() + " \\\\ " + trade.getPrice() +
                    " \\\\ " + trade.getSender().getOwner().getUsername() + " \\\\ " +
                    trade.getReceiver().getOwner().getUsername() + " \\\\ " + trade.isAccepted() + " \\\\ " +
                    trade.getSenderMessage() + " \\\\ " + trade.getReceiverMessage());
        }
        return "";
    }

    public static void deleteTrades() {
        ArrayList<Trade> trades = Game.getYetGame().getCurrentKingdom().getTrades();
        for (int i = 0; i < trades.size();) {
            if (!(trades.get(i).getSender().equals(Game.getYetGame().getCurrentKingdom()))) {
                trades.get(i).getSender().getTrades().remove(trades.get(i));
                trades.get(i).getSender().setTradesHistory(trades.get(i));
                trades.get(i).getReceiver().setTradesHistory(trades.get(i));
                trades.get(i).getReceiver().getTrades().remove(trades.get(i));
            } else
                i++;
        }
    }

    private static Kingdom getUserByNameInThisGame(String name) {
        for (Kingdom kingdom : Game.getYetGame().getPlayers())
            if (kingdom.getOwner().getUsername().equals(name))
                return kingdom;
        return null;
    }
}
