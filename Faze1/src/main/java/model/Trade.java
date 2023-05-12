package model;

import model.Property.Food;
import model.Property.Property;
import model.Property.Resources;
import model.Property.Weapon;

public class Trade {
    private Property property;
    private int resourceAmount;
    private int price;
    private String senderMessage;
    private Kingdom sender;
    private Kingdom receiver;

    private String receiverMessage;

    private boolean accept;

    private static Trade getTradeById(String id) {
        return null;
    }

    public Trade(Property property, int resourceAmount, int price, String message, Kingdom sender) {
        this.property = property;
        this.resourceAmount = resourceAmount;
        this.price = price;
        this.senderMessage = message;
        this.sender = sender;
        this.receiver = null;
        this.accept = false;
        receiverMessage = "";
    }

    public String isAccepted() {
        if (accept)
            return "true";
        return "false";
    }

    public Property getProperty() {
        return property;
    }

    public int getResourceAmount() {
        return resourceAmount;
    }

    public int getPrice() {
        return price;
    }

    public String getSenderMessage() {
        return senderMessage;
    }

    public Kingdom getSender() {
        return sender;
    }

    public Kingdom getReceiver() {
        return receiver;
    }

    public String getReceiverMessage() {
        return receiverMessage;
    }

    public void doTrade(String receiverMessage) {
        if (property instanceof Food)
            receiver.getPropertyByName(((Food) property).getType().name()).addValue(-1 * resourceAmount);
        else if (property instanceof Resources)
            receiver.getPropertyByName(((Resources) property).getResourceType().name()).addValue(-1 * resourceAmount);
        else if (property instanceof Weapon)
            receiver.getPropertyByName(((Weapon) property).getWeaponType().name()).addValue(-1 * resourceAmount);
        property.addValue(resourceAmount);
        sender.addGold(-1 * price);
        receiver.addGold(resourceAmount);
        sender.getTrades().remove(this);
        receiver.getTrades().remove(this);
        sender.setTrades(this);
        receiver.setTrades(this);
        this.accept = true;
        this.receiverMessage = receiverMessage;
    }

}
